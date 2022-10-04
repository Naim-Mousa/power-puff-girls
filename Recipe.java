import java.lang.*;
import java.util.*;
import java.util.Arrays;

// Recipe class where each instance of a recipe will be created
class Recipe{

	private String name;
	private String description;
	private String ingredients;
	private String [] instructions;

	// Constructor method
	Recipe(String name, String description, String ingredients, String [] instructions){

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

		System.out.println("#---------------------------------#");
		System.out.println(this.name);
		System.out.println(this.description);
		System.out.println(this.ingredients);
		
		for (String i : this.instructions){
			System.out.println(i);
		}

		System.out.println("#---------------------------------#");

	}



}


