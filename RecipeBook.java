import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;


public class RecipeBook{

	// added the IO exception, which was needed to create a file in your disk from users input
	public static void main(String[] args) throws FileNotFoundException,IOException{

		// implementing a dictionary using HashMap 
		// keys = recipe name 
		// value = recipe object
		Map <String, Recipe> recipes = new HashMap <String, Recipe>();

		System.out.println("___________________________________");
		System.out.println("===================================");
		System.out.println("Hi! Welcome to your Recipe Book!");
		System.out.println("To exit the program, just type 'exit' at any time.");
		System.out.println("To go back to the previous prompt, just type 'back'.");
		System.out.println("___________________________________");
		System.out.println("===================================");
		
		Scanner scan = new Scanner(System.in);
		String input = "";
		
		// Running an infinite while loop until given command to exit
		// All prompts run continuously until the user types "exit"
		while (!input.equals("exit")){
			
			System.out.println();
			System.out.println("###################################");
			System.out.print("Would like to upload a recipe or search for one (type 'upload' or 'search')? ");
			input = scan.nextLine();

			if (input.equals("upload")){

				while(!input.equals("exit") && !input.equals("back")){

					System.out.println();
					System.out.println("-----------------------------------");
					System.out.println("There are two ways to upload a recipe:");
					System.out.println("1) Upload a text file\n2) Enter each part of the recipe individually");
					System.out.println("-----------------------------------");

					System.out.println();
					System.out.println("###################################");
					System.out.print("Would like to upload a recipe text file, or input the recipe one section at a time (type 'file' or 'section')? ");
					input = scan.nextLine();

					if (input.equals("file")){

						System.out.println();
						System.out.println("-----------------------------------");
						System.out.println("\n To upload a recipe using a text file, you must first create a .txt file which includes:");
						System.out.println("1) Recipe name\n2) Description\n3) Ingredients list\n4) Step-by-step instructions");
						System.out.println("Make sure to follow the template in the recipe_template.txt file to format your recipe file correctly.");
						System.out.println("Once you have your recipe textfile completed and format it correctly, simply input the name of the text file.");
						System.out.println();
						System.out.println("To edit an existing recipe, you must upload a text file that:");
						System.out.println("1) Has the same recipe name in the first line of the text file");
						System.out.println("2) Is formatted in the same way as mentioned in steps 1-4 above");
						System.out.println("Once you have made your desired changes, symply input the name of the text file.");
						System.out.println("-----------------------------------");
						System.out.println();
						System.out.println("Enter the name of your recipe text file: ");

						input = scan.nextLine();

						if (isRecipeSaved(recipes, input)){
							editRecipe(recipes, input);
							break;
						}
						
						// Creating Recipe object
						else if (!input.equals("exit")){
							createRecipe(recipes,input);
							break;
						}

						else break;
					}

					else if (input.equals("section")){
						inputRecipe(recipes);
						break;
					}

					else if (input.equals("exit") || input.equals("back")) break;

					else{ 
						System.out.println("-----------------------------------");
						System.out.println("Invalid input, try again.");
						System.out.println("-----------------------------------");
						continue;
					}
				}

				continue;				
			}

			// Searching for recipe
			else if(input.equals("search")){

				while (!input.equals("exit") && !input.equals("back")){

					System.out.println();
					System.out.println("###################################");
					System.out.print("Would you like to see the catalog of recipe names or search by recipe name (type 'catalog' or 'name') ");
					input = scan.nextLine();

					// Printing full catalog of recipe names
					if (input.equals("catalog")){
						printAllRecipesNames(recipes);

						while (!input.equals("exit") && !input.equals("back")){

							System.out.println();
							System.out.println("###################################");
							System.out.print("Type the name of the recipe you would like to view? ");

							input = scan.nextLine();

							Recipe recipe = findRecipe(input, recipes);

							if ((recipe != null) && (!input.equals("exit"))){

								System.out.println();
								System.out.println("###################################");
								System.out.print("Type '1' to view the entire recipe or '2' to view the instructions step-by-step: ");

								input = scan.nextLine();

								if (input.equals("1")){
									recipe.displayRecipe();
									
									// To send user back to the first prompt in the beginning
									input = "back";
									break;
								}

								else if(input.equals("2")){
									recipe.displaySteps();

									// To send user back to the first prompt in the beginning
									input = "back";
									break;
								}

								else if(input.equals("exit")) break;

								else{
									System.out.println("-----------------------------------");
									System.out.println("Invalid input, try again.");
									System.out.println("-----------------------------------");
									continue;
								}
							}

							else if (input.equals("exit") || input.equals("back")) break;

							else{
								System.out.println("-----------------------------------");
								System.out.println("Invalid input, try again.");
								System.out.println("-----------------------------------");
								continue;					
							}
						}
					}

					// Searching by name
					else if(input.equals("name")){

						while (!input.equals("exit") && !input.equals("back")){

							System.out.println();
							System.out.println("###################################");
							System.out.print("What is the name of the recipe? ");

							input = scan.nextLine();

							// Finding the recipe the user entered
							Recipe recipe = findRecipe(input, recipes);

							// Making sure recipe exists
							if (recipe != null){

								System.out.println();
								System.out.println("###################################");
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

								else if(input.equals("exit") || input.equals("back")) break;

								else{
									System.out.println("-----------------------------------");
									System.out.println("Invalid input, try again.");
									System.out.println("-----------------------------------");
									continue;
								}
							}
						}
					}

					else if (input.equals("exit") || input.equals("back")) break;

					else{
						System.out.println("-----------------------------------");
						System.out.println("Invalid input, try again.");
						System.out.println("-----------------------------------");
						continue;					
					}
				}
			}

			else if(input.equals("exit") || input.equals("back")) break;

			else{
				System.out.println("-----------------------------------");
				System.out.println("Invalid input, try again.");
				System.out.println("-----------------------------------");
				continue;
			}
		}
		
	}

/*
###########################################################################
###########################################################################
##########################        METHODS         #########################
###########################################################################
###########################################################################
*/
	
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
		String recipeName  = textFile.nextLine();
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


	// METHOD: isRecipeSaved
  	// INPUT: recipes database (Map), file name (String)
  	// PROCESSING: reads the first line of the file (recipe name)
  	//			   and checks if the recipe is already in the database
  	// OUTPUT: returns TRUE if the recipe exists, FALSE if it is not
	public static Boolean isRecipeSaved(Map <String, Recipe> recipes, String fileName) throws FileNotFoundException{

		// using Scanner to read the text file 
		File file = new File(fileName);
		Scanner textFile = new Scanner(file);
		
		// read the text file information as Strings
		String recipeName  = textFile.nextLine();

		if (findRecipe(recipeName, recipes) != null) return true;

		else return false;
	}


	// METHOD: editRecipe
  	// INPUT: recipes database (Map), file name (String)
  	// PROCESSING: reads the first line of the file (recipe name)
  	// and locates the object of the recipe that is saved in the database.
  	// Then, it read through the file and assigns the new data to be saved in the
  	// atttributes for that Recipe object
  	// OUTPUT: none
	public static void editRecipe(Map <String, Recipe> recipes, String fileName) throws FileNotFoundException{

		// using Scanner to read the text file 
		File file = new File(fileName);
		Scanner textFile = new Scanner(file);

		// read the text file information as Strings
		String recipeName  = textFile.nextLine();
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

		Recipe recipe = findRecipe(recipeName, recipes);

		recipe.setDescrip(description);
		recipe.setIngred(ingredients);
		recipe.setInstruct(instructions);

		System.out.println("Recipe succesfully edited!");

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
	// also I added the IO exception, which was needed to create a file in your disk from users input
	public static void inputRecipe(Map <String, Recipe> recipes) throws IOException{

	Scanner input = new Scanner(System.in);
	
	System.out.print("\nEnter the recipe name: ");
	String recipeName = input.nextLine();
	
	// ask user to enter descritpion as one big paragraph 
	System.out.println("Insert description as one big paragraph where each sentence is separate by a period.\nAlso don't add a space before the start of a new sentence as shown below\n(ex. Hi my name is Nicole.I am creating a new recipe.This recipe is very good.)");
	System.out.print("Enter the description:  ");
	String strDescription = input.nextLine();
	
	// ask user to enter ingredients in a comma seperated line
	System.out.println("Insert all ingredients in one line where each ingredient is separated by a comma.\nAlso don't add a space after the comma (ex. Apple,bread,milk)");
	System.out.print("Enter the ingredients: ");
	String strIngredients = input.nextLine();
	
	// ask user to enter instructions as one paragraph
	System.out.println("Insert instructions as one big paragraph where each sentence (instruction) is separated by a period.\nAlso don't add a space before the start of a new sentence as shown below\n(ex. Hi my name is Nicole.I am creating a new recipe.This recipe is very good.)");
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
	
	// When a user creates a recipe, it should be saved to a disk in a location within your Git repo

	// allows us to use the recipe name inputted by the user as the file name without the extra spaces in between
	String fileName = recipeName.replaceAll("\\s","_");
	fileName+=".txt";

	// allows us to create a new file with the recipe name 
	File file = new File(fileName);
	FileWriter writer  = new FileWriter(file);
	PrintWriter printToFile = new PrintWriter(writer);

	// printToFile allows us to write to the new file created
	// we will write the recipe name as the first line
	// second line description
	// third line ingredients
	// fourth line instructions
	printToFile.println(recipeName);
	printToFile.println(strDescription);
	printToFile.println(strIngredients);
	printToFile.println(strInstruction);
	printToFile.close();
	
	}

}


