/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package client;

import model.*;
import java.util.*;
import adapter.Debuggable;

public class SelectCarOptions implements Debuggable {
	private Scanner in = new Scanner(System.in);

	public SelectCarOptions() {}

	public void configureAuto(Object obj) {
		Automobile auto = (Automobile) obj;
		float tempPrice = 0f;
		float totalPrice = (float) auto.getBasePrice();
		

		System.out.printf("Received %d %s %s with a $%.2f base price. "
				+ "Please make selections to configure.\n\n\n", 
				auto.getYear(), auto.getMake(), auto.getModel(), auto.getBasePrice());
		
		Automobile configAuto = new Automobile(auto.getMake(), auto.getModel(), auto.getYear(), auto.getBasePrice(), auto.getOptSetLength());
		
		// Make a selection for an Option for each Option Set
		for (int i = 0; i < auto.getOptSetLength(); i++)
		{
			boolean updateYear = false;
			OptionSet tempOS = new OptionSet(auto.getOptSetName(i), 1);
			configAuto.addOneOptSet(tempOS);
			
			if (auto.getOptSetName(i).equals("Year"))
			{
				updateYear = true;
			}
			
			System.out.print("Select option for " + auto.getOptSetName(i) + ":\n");
			for (int i2 = 0; i2 < auto.getOptLength(i); i2++)
			{
				System.out.println("   " + auto.getOptName(i, i2));
				if (i2 == auto.getOptLength(i)-1)
				{
					System.out.print("\nSelection: ");
				}
			}
			String choice = in.nextLine();
			
			for (int i3 = 0; i3 < auto.getOptLength(i); i3++)
			{
				if (auto.getOptName(i, i3).equals(choice))
				{
					tempPrice = auto.getOptPrice(i, i3);
					i3 = auto.getOptLength(i);
					totalPrice += tempPrice;
				}
			}
			configAuto.setOneOptSetOpt(i, 0, choice, tempPrice);
			if (updateYear)
			{
				configAuto.setYear(Integer.parseInt(choice));
			}
			
			System.out.println("Price: $" + tempPrice);
			System.out.println();
		}
		
		System.out.println("\nConfiguration complete.\nConfigured automobile:\n");
		
		// Output the configured Automobile
		configAuto.printMakeModel();
		System.out.printf("Total price: $%.0f\n", totalPrice);
		configAuto.printYear();
		System.out.printf("\n");
		for (int i = 0; i < configAuto.getOptSetLength(); i++)
		{
			configAuto.printOneOptSet(i);
			configAuto.printOneOpt(i, 0);
		}
		
		System.out.println("\n\nPress any key to return to main menu");
	}

	// isAutomobile - Check if the object is an Automobile object
	public boolean isAutomobile(Object obj) {
		boolean isAutomobile = false;

		try {
			Automobile a1 = (Automobile) obj;
			isAutomobile = true;
		}
		catch (ClassCastException e) {
			isAutomobile = false;
		}

		return isAutomobile;
	}
}