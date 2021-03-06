package org.pitest.pitclipse.pitrunner;

import static java.lang.Integer.toHexString;
import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.pitest.pitclipse.reloc.guava.collect.ImmutableList.of;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.pitest.pitclipse.example.ExampleTest;
import org.pitest.pitclipse.pitrunner.PitOptions.PitLaunchException;

public class PitOptionsTest {

	private static final File TMP_DIR = new File(System.getProperty("java.io.tmpdir"));
	private final Random random = new Random();
	private final File testTmpDir = randomDir();
	private final File testSrcDir = randomDir();
	private final File anotherTestSrcDir = randomDir();
	private static final File REALLY_BAD_PATH = getBadPath();

	private static File getBadPath() {
		if (IS_OS_WINDOWS) {
			return new File("BADDRIVE:\\");
		}
		return new File("/HOPEFULLY/DOES/NOT/EXIST/SO/IS/BAD/");
	}

	private static final String TEST_CLASS1 = PitOptionsTest.class.getCanonicalName();
	private static final String TEST_CLASS2 = PitRunner.class.getCanonicalName();
	private static final List<String> CLASS_PATH = of(TEST_CLASS1, TEST_CLASS2);
	private static final String PACKAGE_1 = PitOptionsTest.class.getPackage().getName() + ".*";
	private static final String PACKAGE_2 = ExampleTest.class.getPackage().getName() + ".*";
	private static final List<String> PACKAGES = of(PACKAGE_1, PACKAGE_2);
	private final File historyLocation = randomFile();

	@Before
	public void setup() {
		for (File dir : of(testTmpDir, testSrcDir, anotherTestSrcDir)) {
			dir.mkdirs();
			dir.deleteOnExit();
		}
	}

	@AfterClass
	public static void cleanupFiles() {

	}

	@Test(expected = PitLaunchException.class)
	public void defaultOptionsThrowException() throws IOException {
		PitOptions.builder().build();
	}

	@Test(expected = PitLaunchException.class)
	public void validSourceDirButNoTestClassThrowsException() throws IOException {
		PitOptions.builder().withSourceDirectory(testSrcDir).build();
	}

	@Test
	public void minimumOptionsSet() throws IOException {
		PitOptions options = PitOptions.builder().withSourceDirectory(testSrcDir).withClassUnderTest(TEST_CLASS1)
				.build();
		File reportDir = options.getReportDirectory();
		assertTrue(reportDir.isDirectory());
		assertTrue(reportDir.exists());
		assertEquals(TMP_DIR, reportDir.getParentFile());
		assertEquals(TEST_CLASS1, options.getClassUnderTest());
	}

	@Test(expected = PitLaunchException.class)
	public void sourceDirectoryDoesNotExist() throws IOException {
		PitOptions.builder().withSourceDirectory(randomDir()).withClassUnderTest(TEST_CLASS1).build();
	}

	@Test(expected = PitLaunchException.class)
	public void multipleSourceDirectoriesOneDoesNotExist() throws IOException {
		PitOptions.builder().withSourceDirectories(of(testSrcDir, randomDir())).withClassUnderTest(TEST_CLASS1).build();
	}

	@Test(expected = PitLaunchException.class)
	public void useInvalidReportDirectory() {
		PitOptions.builder().withReportDirectory(REALLY_BAD_PATH).withSourceDirectory(testSrcDir)
				.withClassUnderTest(TEST_CLASS1).build();
	}

	@Test(expected = PitLaunchException.class)
	public void useInvalidSourceDirectory() {
		PitOptions.builder().withSourceDirectory(REALLY_BAD_PATH).withClassUnderTest(TEST_CLASS1).build();
	}

	@Test
	public void useClasspath() throws IOException {
		PitOptions options = PitOptions.builder().withSourceDirectory(testSrcDir).withClassUnderTest(TEST_CLASS1)
				.withClassesToMutate(CLASS_PATH).build();
		File reportDir = options.getReportDirectory();
		assertTrue(reportDir.isDirectory());
		assertTrue(reportDir.exists());
		assertEquals(TMP_DIR, reportDir.getParentFile());
		assertEquals(TEST_CLASS1, options.getClassUnderTest());
	}

	@Test
	public void testPackagesSupplied() {
		PitOptions options = PitOptions.builder().withSourceDirectory(testSrcDir).withPackagesToTest(PACKAGES)
				.withClassesToMutate(CLASS_PATH).build();
		File reportDir = options.getReportDirectory();
		assertTrue(reportDir.isDirectory());
		assertTrue(reportDir.exists());
		assertEquals(TMP_DIR, reportDir.getParentFile());
		assertNull(options.getClassUnderTest());
		assertEquals(PACKAGES, options.getPackages());
	}

	@Test(expected = PitLaunchException.class)
	public void invalidHistoryFileSupplied() {
		PitOptions.builder().withSourceDirectory(testSrcDir).withPackagesToTest(PACKAGES)
				.withClassesToMutate(CLASS_PATH).withHistoryLocation(getBadPath()).build();
	}

	@Test
	public void validHistoryLocationSupplied() {
		PitOptions options = PitOptions.builder().withSourceDirectory(testSrcDir).withPackagesToTest(PACKAGES)
				.withClassesToMutate(CLASS_PATH).withHistoryLocation(historyLocation).build();
		File location = options.getHistoryLocation();
		assertFalse(location.isDirectory());
		assertTrue(location.getParentFile().exists());
	}

	private String randomString() {
		return toHexString(random.nextInt());
	}

	private File randomDir() {
		File randomDir = new File(TMP_DIR, randomString());
		randomDir.deleteOnExit();
		return randomDir;
	}

	private File randomFile() {
		File randomFile = new File(randomDir(), randomString());
		randomFile.deleteOnExit();
		return randomFile;
	}
}
