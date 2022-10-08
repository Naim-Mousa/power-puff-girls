import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		// implementing a dictionary using HashMap 
		// keys = recipe name 
		// value = recipe object
		Map <String, Recipe> recipes = new HashMap <String, Recipe>();
		
	}
	
	
	/* createRecipe information about parameters: */
	
	// fileName parameter description: 
	// fileName is a String which is the name of your recipe txt file with its txt extension (i.e. pizza.txt)
	// only if your recipe txt file is in the same directory of this program. Now if this recipe txt file is not in the same
	// directory of this program, then fileName will be the full path of your recipe text file
	// (this is from geeksforgeeks i.e.C:\\Users\\pankaj\\Desktop\\test.txt")
	

	// recipies parameter description:
	// you just have to pass in our dictionary called recipes created in main
	
	public static void createRecipe(Map <String, Recipe> recipes,String fileName) throws FileNotFoundException {
		
		// using Scanner to read the text file 
		File file = new File(fileName);
		Scanner textFile = new Scanner(file);
		
		// read the text file information as Strings
		String recipeName  = textFile.nextLine() ;
		String strDescription = textFile.nextLine();
		String strIngredients = textFile.nextLine();
		String strInstruction = textFile.nextLine();
	
		// Since description is one big paragraph, we will get the individual sentences by using the split method
		String [] description = strDescription.split("\\.");
		
		// Since ingredients is one big line where ingredients are seperated by commas, 
		// we can get the individual ingredients using the split method
		String [] ingredients = strIngredients.split(",");
		
		// Since instructions is one big paragraph, we will get the individual sentences by using the split method
		String [] instructions = strInstruction.split("\\."); 
		
		// Now store the recipe information into a Recipe object
		Recipe newRecipe = new Recipe(recipeName, description, ingredients, instructions);

		// add this recipe object into our dictionary recipes
		recipes.put(recipeName, newRecipe);
		
			
		
	}
	
	// recipies parameter description:
	// you just have to pass in our dictionary called recipes in main
	
	public static void printAllRecipesNames(Map <String, Recipe> recipes) {
		
		System.out.println("\nBrowse All Recipes:");
		
		// print all the recipe names
		for(String recipeName: recipes.keySet()){
			
		    System.out.println(" - "+recipeName);
		}
		
		
	}
	
	
	
	// I commented this out because I am not sure if you guys want to use it or not 
	// displays the main UI the user interacts with 
	/*
	public static void displayMainWindow() {
		System.out.println("\nRecipe Book:");
		System.out.println("=============");
		System.out.println("Choose one of the following options:");
		System.out.println("\n(1)Add a recipe");
		System.out.println("\n(2)Search for recipe");
		System.out.println("\n(3)Display a recipe ");
		System.out.println("\n(4)Quit");
		System.out.print("\nEnter Your Choice: ");
	}
	*/
	
	


}


