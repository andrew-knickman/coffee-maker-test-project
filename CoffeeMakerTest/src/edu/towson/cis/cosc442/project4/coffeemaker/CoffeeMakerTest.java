package edu.towson.cis.cosc442.project4.coffeemaker;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>CoffeeMakerTest</code> contains tests for the class <code>{@link CoffeeMaker}</code>.
 *
 * @generatedBy CodePro at 3/14/19 6:58 PM
 * @author Andrew Knickman
 * @version $Revision: 1.0 $
 */
public class CoffeeMakerTest {
	/**Test to confirm addition of new recipe with set variables*/
	@Test
	public void addRecipe1() throws Exception{
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe r = new Recipe();
		r.setName("Coffee");
		assertEquals(r.getName(), "Coffee");
		r.setPrice(50);
		assertEquals(50, r.getPrice());
		r.setAmtCoffee(3);
		assertEquals(3, r.getAmtCoffee());
		r.setAmtMilk(1);
		assertEquals(1, r.getAmtMilk());
		r.setAmtSugar(1);
		assertEquals(1, r.getAmtSugar());
		r.setAmtChocolate(0);
		assertEquals(0, r.getAmtChocolate());
		
		boolean result = fixture.addRecipe(r);
		
		assertTrue(result);
	}
	
	/**Test to confirm recipe with non-unique name can't be added*/
	@Test
	public void addRecipe2() throws Exception{
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe r1 = new Recipe();
		r1.setName("Coffee");
		assertEquals(r1.getName(), "Coffee");
		Recipe r2 = new Recipe();
		r2.setName("Coffee");
		assertEquals(r2.getName(), "Coffee");
		
		fixture.addRecipe(r1);
		boolean result = fixture.addRecipe(r2);
		
		assertFalse(result);
	}
	
	
	/**Test to confirm recipe cannot be added if no more slots remain*/
	@Test
	public void addRecipe3() throws Exception{
		CoffeeMaker fixture = new CoffeeMaker();

		fixture.addRecipe(new Recipe());
		fixture.addRecipe(new Recipe());
		fixture.addRecipe(new Recipe());
		fixture.addRecipe(new Recipe());
		
		boolean result = fixture.addRecipe(new Recipe());
		assertFalse(result);
	}
	
	/**Test to confirm deletion of recipe*/
	@Test
	public void deleteRecipe1() throws Exception{
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe r1 = new Recipe();
		r1.setName("Coffee");
		fixture.addRecipe(r1);
		Recipe r2 = r1;
		assertEquals(r1, r2);
		
		boolean result = fixture.deleteRecipe(r2);
		assertTrue(result);
	}
	
	/**Test to confirm deleted recipe can't be deleted again*/
	@Test
	public void deleteRecipe2() throws Exception{
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe r = new Recipe();
		fixture.addRecipe(r);
		fixture.deleteRecipe(r);
		assertFalse(fixture.deleteRecipe(r));
	}
	
	/**Test to confirm a recipe can be edited*/
	@Test
	public void editRecipe1() throws Exception{
		CoffeeMaker fixture = new CoffeeMaker();
		
		Recipe r1 = new Recipe();
		r1.setName("Coffee");
		r1.setPrice(50);
		r1.setAmtCoffee(3);
		r1.setAmtMilk(1);
		r1.setAmtSugar(1);
		r1.setAmtChocolate(0);
		
		fixture.addRecipe(r1);
		
		Recipe r2 = new Recipe();
		r2.setName("Coffee");
		r2.setPrice(50);
		r2.setAmtCoffee(3);
		r2.setAmtMilk(2);
		r2.setAmtSugar(2);
		r2.setAmtChocolate(0);
		
		boolean result = fixture.editRecipe(r1, r2);
		assertTrue(result);
	}
	
	/**Test to confirm a deleted recipe can't be edited*/
	@Test
	public void editRecipe2() throws Exception{
		CoffeeMaker fixture = new CoffeeMaker();
		
		Recipe r1 = new Recipe();
		Recipe r2 = new Recipe();
		
		fixture.addRecipe(r1);
		fixture.deleteRecipe(r1);
		boolean result = fixture.editRecipe(r1, r2);
		assertFalse(result);
	}
	
	/**Test to confirm recipe name can't be changed*/
	@Test
	public void editRecipe3() throws Exception{
		CoffeeMaker fixture = new CoffeeMaker();
		
		Recipe r1 = new Recipe();
		r1.setName("Coffee");
		r1.setAmtCoffee(3);
		r1.setAmtMilk(1);
		r1.setAmtSugar(1);
		r1.setAmtChocolate(0);
		
		Recipe r2 = r1;
		r2.setName("Decaf");
		
		fixture.addRecipe(r1);
		
		boolean result = fixture.editRecipe(r1, r2);
		assertTrue(result);
	}
	
	/**Test to confirm addition of inventory*/
	@Test
	public void addInventory1() throws Exception{
		CoffeeMaker fixture = new CoffeeMaker();
		
		boolean result = fixture.addInventory(3,3,3,3);
		assertTrue(result);
	}
	
	/**Test to confirm inventory cannot be subtracted*/
	@Test
	public void addInventory2() throws Exception{
		CoffeeMaker fixture = new CoffeeMaker();
		boolean result = fixture.addInventory(-3, -3, -3, -3);
		assertFalse(result);
	}
	
	/**Test to confirm inventory is returned*/
	@Test
	public void checkInventory() throws Exception{
		CoffeeMaker fixture = new CoffeeMaker();
		Inventory i = fixture.checkInventory();
		assertEquals(i,i);
	}
	
	/**Test to confirm coffee can be purchased*/
	@Test
	public void makeCoffee1() throws Exception{
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe r = new Recipe();
		r.setName("Coffee");
		r.setPrice(50);
		assertEquals(1, fixture.makeCoffee(r,51));
	}
	
	/**Test to confirm coffee can't be purchased with insufficient funds*/
	@Test
	public void makeCoffee2() throws Exception{
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe r = new Recipe();
		r.setName("Coffee");
		r.setPrice(50);
		assertEquals(49,fixture.makeCoffee(r, 49));
	}
	
	/**Test to confirm coffee can't be purchased with missing inventory*/
	@Test
	public void makeCoffee3() throws Exception{
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe r = new Recipe();
		r.setName("Coffee");
		r.setPrice(50);
		r.setAmtCoffee(3);
		r.setAmtMilk(1);
		r.setAmtMilk(1);
		r.setAmtChocolate(0);
		fixture.checkInventory().setCoffee(0);
		fixture.checkInventory().setMilk(0);
		fixture.checkInventory().setSugar(0);
		fixture.checkInventory().setChocolate(0);
		assertEquals(50,fixture.makeCoffee(r, 50));
	}
	
	/**
	 * Run the CoffeeMaker() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testCoffeeMaker_1()
		throws Exception {

		CoffeeMaker result = new CoffeeMaker();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the CoffeeMaker() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testCoffeeMaker_2()
		throws Exception {

		CoffeeMaker result = new CoffeeMaker();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the boolean addInventory(int,int,int,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testAddInventory_1()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		int amtCoffee = 1;
		int amtMilk = 1;
		int amtSugar = 1;
		int amtChocolate = -1;

		boolean result = fixture.addInventory(amtCoffee, amtMilk, amtSugar, amtChocolate);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean addInventory(int,int,int,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testAddInventory_2()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		int amtCoffee = 1;
		int amtMilk = 1;
		int amtSugar = 1;
		int amtChocolate = 1;

		boolean result = fixture.addInventory(amtCoffee, amtMilk, amtSugar, amtChocolate);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean addInventory(int,int,int,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testAddInventory_3()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		int amtCoffee = -1;
		int amtMilk = 1;
		int amtSugar = 1;
		int amtChocolate = 1;

		boolean result = fixture.addInventory(amtCoffee, amtMilk, amtSugar, amtChocolate);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean addInventory(int,int,int,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testAddInventory_4()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		int amtCoffee = 1;
		int amtMilk = -1;
		int amtSugar = 1;
		int amtChocolate = 1;

		boolean result = fixture.addInventory(amtCoffee, amtMilk, amtSugar, amtChocolate);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean addInventory(int,int,int,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testAddInventory_5()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		int amtCoffee = 1;
		int amtMilk = 1;
		int amtSugar = -1;
		int amtChocolate = 1;

		boolean result = fixture.addInventory(amtCoffee, amtMilk, amtSugar, amtChocolate);

		// add additional test code here
		assertEquals(false, result);
	}

	
	/**
	 * Run the boolean addRecipe(Recipe) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testAddRecipe_1()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe r = new Recipe();

		boolean result = fixture.addRecipe(r);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean addRecipe(Recipe) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testAddRecipe_2()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe r = new Recipe();

		boolean result = fixture.addRecipe(r);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean addRecipe(Recipe) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testAddRecipe_3()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe r = new Recipe();

		boolean result = fixture.addRecipe(r);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean checkEmptyRecipe(Recipe,boolean) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testCheckEmptyRecipe_1()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe r = new Recipe();
		boolean canAddRecipe = true;

		boolean result = fixture.checkEmptyRecipe(r, canAddRecipe);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean checkEmptyRecipe(Recipe,boolean) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testCheckEmptyRecipe_2()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe r = new Recipe();
		boolean canAddRecipe = true;

		boolean result = fixture.checkEmptyRecipe(r, canAddRecipe);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean checkEmptyRecipe(Recipe,boolean) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testCheckEmptyRecipe_3()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe r = new Recipe();
		boolean canAddRecipe = true;

		boolean result = fixture.checkEmptyRecipe(r, canAddRecipe);

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean checkEmptyRecipe(Recipe,boolean) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testCheckEmptyRecipe_4()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe r = new Recipe();
		boolean canAddRecipe = false;

		boolean result = fixture.checkEmptyRecipe(r, canAddRecipe);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the Inventory checkInventory() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testCheckInventory_1()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();

		Inventory result = fixture.checkInventory();

		// add additional test code here
		assertNotNull(result);
		assertEquals("Coffee: 15\r\nMilk: 15\r\nSugar: 15\r\nChocolate: 15\r\n", result.toString());
		assertEquals(15, result.getCoffee());
		assertEquals(15, result.getMilk());
		assertEquals(15, result.getSugar());
		assertEquals(15, result.getChocolate());
	}

	/**
	 * Run the boolean deleteRecipe(Recipe) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testDeleteRecipe_1()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe r = new Recipe();

		boolean result = fixture.deleteRecipe(r);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean deleteRecipe(Recipe) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testDeleteRecipe_2()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe r = new Recipe();

		boolean result = fixture.deleteRecipe(r);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean deleteRecipe(Recipe) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testDeleteRecipe_3()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe r = new Recipe();

		boolean result = fixture.deleteRecipe(r);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean deleteRecipe(Recipe) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testDeleteRecipe_4()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe r = null;

		boolean result = fixture.deleteRecipe(r);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean editRecipe(Recipe,Recipe) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testEditRecipe_1()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe oldRecipe = new Recipe();
		Recipe newRecipe = new Recipe();

		boolean result = fixture.editRecipe(oldRecipe, newRecipe);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean editRecipe(Recipe,Recipe) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testEditRecipe_2()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe oldRecipe = new Recipe();
		Recipe newRecipe = new Recipe();

		boolean result = fixture.editRecipe(oldRecipe, newRecipe);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean editRecipe(Recipe,Recipe) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testEditRecipe_3()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe oldRecipe = new Recipe();
		Recipe newRecipe = new Recipe();

		boolean result = fixture.editRecipe(oldRecipe, newRecipe);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean editRecipe(Recipe,Recipe) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testEditRecipe_4()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe oldRecipe = new Recipe();
		Recipe newRecipe = new Recipe();

		boolean result = fixture.editRecipe(oldRecipe, newRecipe);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean editRecipe(Recipe,Recipe) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testEditRecipe_5()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe oldRecipe = new Recipe();
		Recipe newRecipe = new Recipe();

		boolean result = fixture.editRecipe(oldRecipe, newRecipe);

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the Recipe getRecipeForName(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testGetRecipeForName_1()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		String name = "";

		Recipe result = fixture.getRecipeForName(name);

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.toString());
		assertEquals(null, result.getName());
		assertEquals(0, result.getAmtCoffee());
		assertEquals(0, result.getPrice());
		assertEquals(0, result.getAmtSugar());
		assertEquals(0, result.getAmtChocolate());
		assertEquals(0, result.getAmtMilk());
	}

	/**
	 * Run the Recipe getRecipeForName(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testGetRecipeForName_2()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		String name = "";

		Recipe result = fixture.getRecipeForName(name);

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.toString());
		assertEquals(null, result.getName());
		assertEquals(0, result.getAmtCoffee());
		assertEquals(0, result.getPrice());
		assertEquals(0, result.getAmtSugar());
		assertEquals(0, result.getAmtChocolate());
		assertEquals(0, result.getAmtMilk());
	}

	/**
	 * Run the Recipe getRecipeForName(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testGetRecipeForName_3()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		String name = "";

		Recipe result = fixture.getRecipeForName(name);

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.toString());
		assertEquals(null, result.getName());
		assertEquals(0, result.getAmtCoffee());
		assertEquals(0, result.getPrice());
		assertEquals(0, result.getAmtSugar());
		assertEquals(0, result.getAmtChocolate());
		assertEquals(0, result.getAmtMilk());
	}

	/**
	 * Run the Recipe getRecipeForName(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testGetRecipeForName_4()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		String name = "";

		Recipe result = fixture.getRecipeForName(name);

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.toString());
		assertEquals(null, result.getName());
		assertEquals(0, result.getAmtCoffee());
		assertEquals(0, result.getPrice());
		assertEquals(0, result.getAmtSugar());
		assertEquals(0, result.getAmtChocolate());
		assertEquals(0, result.getAmtMilk());
	}

	/**
	 * Run the Recipe[] getRecipes() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testGetRecipes_1()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();

		Recipe[] result = fixture.getRecipes();

		// add additional test code here
		assertNotNull(result);
		assertEquals(4, result.length);
		assertNotNull(result[0]);
		assertEquals(null, result[0].toString());
		assertEquals(null, result[0].getName());
		assertEquals(0, result[0].getAmtCoffee());
		assertEquals(0, result[0].getPrice());
		assertEquals(0, result[0].getAmtSugar());
		assertEquals(0, result[0].getAmtChocolate());
		assertEquals(0, result[0].getAmtMilk());
		assertNotNull(result[1]);
		assertEquals(null, result[1].toString());
		assertEquals(null, result[1].getName());
		assertEquals(0, result[1].getAmtCoffee());
		assertEquals(0, result[1].getPrice());
		assertEquals(0, result[1].getAmtSugar());
		assertEquals(0, result[1].getAmtChocolate());
		assertEquals(0, result[1].getAmtMilk());
		assertNotNull(result[2]);
		assertEquals(null, result[2].toString());
		assertEquals(null, result[2].getName());
		assertEquals(0, result[2].getAmtCoffee());
		assertEquals(0, result[2].getPrice());
		assertEquals(0, result[2].getAmtSugar());
		assertEquals(0, result[2].getAmtChocolate());
		assertEquals(0, result[2].getAmtMilk());
		assertNotNull(result[3]);
		assertEquals(null, result[3].toString());
		assertEquals(null, result[3].getName());
		assertEquals(0, result[3].getAmtCoffee());
		assertEquals(0, result[3].getPrice());
		assertEquals(0, result[3].getAmtSugar());
		assertEquals(0, result[3].getAmtChocolate());
		assertEquals(0, result[3].getAmtMilk());
	}

	/**
	 * Run the int makeCoffee(Recipe,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testMakeCoffee_1()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe r = new Recipe();
		int amtPaid = 1;

		int result = fixture.makeCoffee(r, amtPaid);

		// add additional test code here
		assertEquals(1, result);
	}

	/**
	 * Run the int makeCoffee(Recipe,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Test
	public void testMakeCoffee_2()
		throws Exception {
		CoffeeMaker fixture = new CoffeeMaker();
		Recipe r = new Recipe();
		int amtPaid = 1;

		int result = fixture.makeCoffee(r, amtPaid);

		// add additional test code here
		assertEquals(1, result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 3/14/19 6:58 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CoffeeMakerTest.class);
	}
}