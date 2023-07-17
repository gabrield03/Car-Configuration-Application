/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package exception;

import java.util.Scanner;
import exception.AutoException.ErrorList;

// Helper class
// The actual functionality of fixing missing/bad data exists here
public class Fix1to100 {
	private Scanner inp;
	
	public Fix1to100() {}
	
	// Missing make
	public String fix1(ErrorList error)
	{
		inp = new Scanner(System.in);
		System.out.printf("Missing the make of the automobile.\nPlease enter the make: ");
		String newMake = inp.next();
		System.out.printf("\n");
		
		return newMake;
	}
	
	// Missing model
	public String fix2(ErrorList error)
	{
		inp = new Scanner(System.in);
		System.out.printf("Missing the model of the automobile.\nPlease enter the model: ");
		String newModel = inp.nextLine();
		System.out.printf("\n");
		
		return newModel;
	}
	
	// Missing base price
	public String fix3(ErrorList error)
	{
		inp = new Scanner(System.in);
		System.out.printf("Missing the base price of the automobile.\nPlease enter the base price: $");
		String newBasePrice = inp.next();
		System.out.printf("\n");
		
		return newBasePrice;
	}
	
	// Missing an option set
	public String fix4(ErrorList error, int index)
	{
		inp = new Scanner(System.in);
		System.out.printf("Missing Option Set #%d of the automobile.\nPlease enter the Option Set: ", index+1);
		String newOptionSet = inp.nextLine();
		System.out.printf("\n");
		
		return newOptionSet;
	}
			
	// Missing an option price
	public String fix5(ErrorList error, String name)
	{
		inp = new Scanner(System.in);
		System.out.printf("Missing a price for an Option in Option Set: %s.\nPlease enter the price: $", name);
		String newPrice = inp.next();
		System.out.printf("\n");
		
		return newPrice;
	}
	
	// Missing an option
	public String fix6(ErrorList error, int index1, String name, float price)
	{
		inp = new Scanner(System.in);
		System.out.printf("Missing the option from the Option Set %d: %s priced at $%.2f.\nPlease enter the Option name: ", index1+1, name, price);
		String newOption = inp.nextLine();
		System.out.printf("\n");
		
		return newOption;
	}
}