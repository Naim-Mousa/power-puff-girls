import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;


public class RecipeBook{

	public static void main(String[] args) throws FileNotFoundException{

		// implementing a dictionary using HashMap 
		// keys = recipe name 
		// value = recipe object
		Map <String, Recipe> recipes = new HashMap <String, Recipe>();

		System.out.println("___________________________________");
		System.out.println("===================================");
		System.out.println("Hi! Welcome to your Recipe Book!");
		System.out.println("To exit the program, just type 'exit' at any time.");
		System.out.println("___________________________________");
		System.out.println("===================================");
		
		Scanner scan = new Scanner(System.in);
		String input = "";
		
		// Running an infinite while loop until given command to exit
		// All prompts run continuously until the user types "exit"
		while (!input.equals("exit")){
			
			System.out.print("Would like to upload a recipe or search for one (type 'upload' or 'search')? ");
			input = scan.nextLine();

			if (input.equals("upload")){
				System.out.println("-----------------------------------");
				System.out.println("\n To upload a recipe, you must first create a .txt file which includes:");
				System.out.println("1) Recipe name\n2) Description\n 3) Ingredients list\n 4) Step-by-step instructions");
				System.out.println("Make sure to follow the template in the recipe_template.txt file to format your recipe file correctly.");
				System.out.println("-----------------------------------");
				System.out.print("Once you have your recipe textfile completed and format it correctly, enter its name here: ");


				input = scan.nextLine();
				
				// Creating Recipe object
				if (!input.equals("exit")){
					createRecipe(recipes,input);
				}

				else break;
				continue;				
			}

			// Searching for recipe
			else if(input.equals("search")){

				while (!input.equals("exit")){

					System.out.print("Would you like to see the catalog of recipe names or search by recipe name (type 'catalog' or 'name') ");
					input = scan.nextLine();

					// Printing full catalog of recipe names
					if (input.equals("catalog")){
						printAllRecipesNames(recipes);

						while (!input.equals("exit")){

							System.out.print("Type the name of the recipe you would like to view? ");

							input = scan.nextLine();

							Recipe recipe = findRecipe(input, recipes);

							if ((recipe != null) && (!input.equals("exit"))){

								System.out.print("Type '1' to view the entire recipe or '2' to view the instructions step-by-step: ");

								input = scan.nextLine();

								if (input.equals("1")){
									recipe.displayRecipe();
									continue;
								}

								else if(input.equals("2")){
									recipe.displaySteps();
									continue;
								}

								else if(input.equals("exit")) break;

								else{
									System.out.println("Invalid input, try again.");
									continue;
								}
							}

							else if (input.equals("exit")) break;

							else{
								System.out.println("Invalid input, try again.");
								continue;					
							}
						}
					}

					// Searching by name
					else if(input.equals("name")){

						while (!input.equals("exit")){

							System.out.print("What is the name of the recipe? ");

							input = scan.nextLine();

							// Finding the recipe the user entered
							Recipe recipe = findRecipe(input, recipes);

							// Making sure recipe exists
							if (recipe != null){

								System.out.print("Type '1' to view the entire recipe or '2' to view the instructions step-by-step: ");

								input = scan.nextLine();

								if (input.equals("1")){
									recipe.displayRecipe();
									continue;
								}

								else if(input.equals("2")){
									recipe.displaySteps();
									continue;
								}

								else if(input.equals("exit")) break;

								else{
									System.out.println("-----------------------------------");
									System.out.println("Invalid input, try again.");
									System.out.println("-----------------------------------");
									continue;
								}
							}
						}
					}

					else if (input.equals("exit")) break;

					else{
						System.out.println("-----------------------------------");
						System.out.println("Invalid input, try again.");
						System.out.println("-----------------------------------");
						continue;					
					}
				}
			}

			else if(input.equals("exit")) break;

			else{
				System.out.println("-----------------------------------");
				System.out.println("Invalid input, try again.");
				System.out.println("-----------------------------------");
				continue;
			}
		}
		
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

		System.out.println("Recipe succesfully added!");
		
			
		
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

	public static Recipe findRecipe(String recipeName, Map<String, Recipe> Recipes) {
        for (Map.Entry<String,Recipe> entry : Recipes.entrySet())  {
            if (recipeName.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
    
    	// Allows users to create a recipe by inputting all of the recipe information
    	// via the command line 
    	public static void inputRecipe(Map <String, Recipe> recipes) {
		
		
		/* Naim somewhere in here if you can display a message that tells the user to input their description and instructions 
		 * as one big paragraph where each sentence is separate by a period. Also remind them not to add a space before the start of a new sentence 
		 * like this (ex.Hi my name is Nicole.I am creating a new recipe.The recipe is very good.]
		 * Also for the ingredients, let them know that they should enter the ingredients as one big line 
		 * where each ingredient is separated by a comma. Also don't add a space after the comma (ex. Apple,bread,milk)]
		 */
		Scanner input = new Scanner(System.in);
		
		System.out.print("\nEnter the recipe name: ");
		String recipeName = input.nextLine();
		
		// ask user to enter descritpion as one big paragraph 
		System.out.print("Enter the description:  ");
		String strDescription = input.nextLine();
		
		// ask user to enter ingredients in a comma seperated line
		System.out.print("Enter the ingredients: ");
		String strIngredients = input.nextLine();
		
		// ask user to enter instructions as one paragraph
		System.out.print("Enter the instructions: "); 
		String strInstruction = input.nextLine();
		
		// Since description is one big paragraph, we will get the individual sentences by using the split method
		String [] description = strDescription.split("\\.");
		
		// Since ingredients is one big line where ingredients are seperated by commas, 
		// we can get the individual ingredients using the split method
		String [] ingredients = strIngredients.split(",");
		
		// Since instructions is one big paragraph, we will get the individual sentences by using the split method
		String [] instructions = strInstruction.split("\\."); 
		
		// Now use this recipe information to create a new Recipe object
		Recipe newRecipe = new Recipe(recipeName, description, ingredients, instructions);

		// add this new recipe to our dictionary recipes
		recipes.put(recipeName, newRecipe);
		
	}

}


