
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class Main {

	public static void main(String[] args) {
		
		// arrayList will store all the recipes
		ArrayList <Recipe> recipeList = new ArrayList <> ();
		
		// scanner will be used to recieve the user input
		Scanner scan = new Scanner (System.in);
		
		// show the user their options in this program
		displayMainWindow();
		
		// (1) user choose to add a recipe
		if (scan.nextInt() == 1) {
			addRecipe(recipeList,scan);
			
		}
		
			
			
	}
	
	// displays the main UI the user interacts with 
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
	
	// allows user to add a recipe 
	public static void addRecipe(ArrayList<Recipe> list, Scanner scan) {
		// heading of this window
		System.out.println("\nMain Window");
		System.out.println("============================================================================");
		
		String workAround= scan.nextLine(); // Need this because nextInt messes things up for nextLine
		
		// ask user for the name of the recipe
		System.out.print("Name of recipe: ");
		String name = scan.nextLine();
		
		// ask user for the recipe description
		System.out.print("Decription:");
		String email = scan.nextLine();
		
		// ask user for the ingredients
		System.out.print("Ingredients:");
		String phone = scan.nextLine();
		
		// ask user for instructions 
		System.out.print("Instructions: ");
		String notes = scan.nextLine();
		

	}
	


}


