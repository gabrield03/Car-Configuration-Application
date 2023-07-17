/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package driver;

import adapter.*;
import exception.AutoException;

public class Driver2 {
	public static void main(String[] args) throws AutoException
	{
		int TESTING = 3;
		CreateAuto a2 = new BuildAuto();
		UpdateAuto a3 = new BuildAuto();
		FixAuto a4 = new BuildAuto();
		
		// testing for UpdateAuto
		if (TESTING == 1) {
			a2.buildAuto("P:\\Eclipse-Java\\CIS 35B\\Assignments\\AutoProperties\\FordZTW.txt", ".txt");
			a2.printAuto("Ford", "ZTW");
		}
		
		// Testing for UpdateAuto
		else if (TESTING == 2)
		{
			a2.buildAuto("P:\\Eclipse-Java\\CIS 35B\\Assignments\\AutoProperties\\FordZTW.txt", ".txt");
			System.out.println("~~ BEFORE UPDATING ~~");
			a2.printAuto("Ford", "ZTW");
			
			a3.updateOptionSetName("ZTW", "Power Moonroof", "Power Sunroof");
			a3.updateOptionPrice("ZTW", "Brakes/Traction Control", "ABS", 400);
			System.out.println("~~ AFTER UPDATING ~~");
			a2.printAuto("Ford", "ZTW");
		}

		// Testing for FixAuto
		else if (TESTING == 3)
		{
			a4.buildAutoFromBadFile("P:\\Eclipse-Java\\CIS 35B\\Assignments\\AutoProperties\\FordZTWNoMake.txt", ".txt");
			a2.printAuto("Ford", "ZTW");
		}
	}
}