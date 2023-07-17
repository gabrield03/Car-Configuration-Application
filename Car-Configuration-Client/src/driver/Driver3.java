/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package driver;

import adapter.*;
import exception.AutoException;

public class Driver3 {
	public static void main(String[] args) throws AutoException
	{
		int TESTING = 1;
		CreateAuto a5 = new BuildAuto();
		UpdateAuto a6 = new BuildAuto();
		ChoiceAuto a7 = new BuildAuto();
		
		// Build a Ford ZTW Automobile
		if (TESTING == 1)
		{
			a5.buildAuto("P:\\Eclipse-Java\\CIS 35B\\Assignments\\AutoProperties\\FordZTW.txt", ".txt");
		}
		
		// Build an Aston Martin Vantage
		else if (TESTING == 2)
		{
			a5.buildAuto("P:\\Eclipse-Java\\CIS 35B\\Assignments\\AutoProperties\\AstonMartinVantage.txt", ".txt");
		}
		a7.setOptionChoice();
		a7.printTotalOptionPrice();
		a7.printLHashMap();
		a6.removeEntry("Aston MartinVantage");
		a7.printLHashMap();
	}
}