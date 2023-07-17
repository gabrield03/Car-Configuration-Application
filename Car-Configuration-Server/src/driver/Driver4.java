/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package driver;

import adapter.*;
import exception.AutoException;

public class Driver4 {
	public static void main(String[] args) throws AutoException
	{
		int TESTING = 1;
		
		CreateAuto a1 = new BuildAuto();
		a1.buildAuto("P:\\Eclipse-Java\\CIS 35B\\Assignments\\AutoProperties\\FordZTW.txt", ".txt");
		
		EditAuto ea1 = new BuildAuto();
		String[] args1 = {"Infra-Red Clearcoat", "GREEN-GOLD GLAZE"};
		String[] args2 = {"Infra-Red Clearcoat", "BLACK-BLUE BLAZE"};
		
		// Test unsynchronized methods
		if (TESTING == 1)
		{
			ea1.editThread("Ford ZTW", 0, args1, -1);
			ea1.editThread("Ford ZTW", 1, args2, -1);
		}
		
		// Test synchronized methods
		else if (TESTING == 2)
		{
			ea1.editThread("Ford ZTW", 2, args1, 1000);
			ea1.editThread("Ford ZTW", 3, args2, 2000);
		}
	}
}