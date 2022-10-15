import java.util.Map;
import java.util.Scanner;
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
	Recipe(String name, String[] description, String[] ingredients, String[] instructions){

		this.name = name;
		this.description = description;
		this.ingredients = ingredients;
		this.instructions = instructions;

	}

	// Getter methods for each attribute
	public String getName(){
		return this.name;
	}
	
	public String[] getDescrip(){
		return this.description;
	}

	public String[] getIngred(){
		return this.ingredients;
	}
	
	public String[] getInstruct(){
		return this.instructions;
	}

	// Setter methods for each attribute
	public void setName(String name){
		this.name = name;
	}
	
	public void setDescrip(String[] description){
		this.description = description;
	}

	public void setIngred(String[] ingredients){
		this.ingredients = ingredients;
	}
	
	public void setInstruct(String [] instructions){
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

	// METHOD: displaySteps
  	// INPUT: none
  	// PROCESSING: Takes the instance object of a recipe
  	// and iterates through its step-by-step instructions.
  	// OUTPUT: Each step of the recipe after the user prompts it to be printed.
    public void displaySteps(){
	    for (String instruction : this.instructions) {
	        System.out.println(instruction + '\n');
	        System.out.println("Press \"ENTER\" to continue... or type \"QUIT\" to exit the instructions.");
	        Scanner scanner = new Scanner(System.in);
	        String readString = scanner.nextLine();
	        if (readString.isEmpty()) {
	            continue;
	        } else {
	            break;
	        }
	    }
	}

}





