/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package driver;

import exception.AutoException;
import model.Automobile;
import util.FileIO;

public class Driver1 {

	public static void main(String[] args) throws AutoException
	{
		FileIO file = new FileIO("P:\\Eclipse-Java\\CIS 35B\\Assignments\\AutoProperties\\FordZTW.txt");
		
		// Build the Automobile object from a file
		Automobile a1 = file.buildAutoObject("P:\\Eclipse-Java\\CIS 35B\\Assignments\\AutoProperties\\FordZTW.txt", ".txt");
		
		// Print attributes before serialization
		a1.printData();
		
		// Serialize the object
		String serializedAutoFile = file.serializeData(a1);
		
		// Deserialize the object and read it into memory
		Automobile newA1 = file.deserializeData(serializedAutoFile);
		
		// Print the new object's attributes after deserialization
		newA1.printData();
	}
}