
import java.lang.*;
import java.util.*;
import java.util.Arrays;

// Recipe class where each instance of a recipe will be created
class Recipe{

	private String name;
	private String[] description;
	private String []ingredients;
	private String [] instructions;

	
	
	// Constructor method
	Recipe(String name, String[] description, String [] ingredients, String [] instructions){

		this.name = name;
		this.description = description;
		this.ingredients = ingredients;
		this.instructions = instructions;

	}
	
	// METHOD: displayRecipe
  	// INPUT: none
  	// PROCESSING: Takes attributes of instance of Recipe class
  	// OUTPUT: Prints out all attributes (entire recipe)
	public void displayRecipe(){

		System.out.println("\nRecipe name: "+this.name+"\n");
		System.out.println("Description: ");
		
		// iterate throught the description array and print each sentence on its own line 
		for (String i : this.description){
			System.out.println(i+".");
		}
		
		System.out.println();
		
		// iterate through the inredients array and print each ingredient on its own line 
		System.out.println("Ingredients:");
		for (String i : this.ingredients){
			System.out.println(" - "+i);
		}
		
		System.out.println();
		
		// iterate through the instructions array and print each instruction as a numbered list 
		System.out.println("Instructions:");
		int num = 1;
		for (String i : this.instructions){
			System.out.println(" "+num+". "+i+".");
			num++;
		}

		

	}



}