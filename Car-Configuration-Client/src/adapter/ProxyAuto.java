/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package adapter;

import exception.AutoException;
import model.*;
import util.FileIO;
import java.util.Properties;
import java.util.Scanner;
import client.DefaultSocketClient;
import scale.EditOptions;

public abstract class ProxyAuto {
	private static Automobile a1;
	private static FileIO file;
	private static LHMAuto<String, Automobile> autoLHM = new LHMAuto<String, Automobile>();
	
	// Given a text file name a method called BuildAuto can be written to build an instance of Automobile
	public void buildAuto(String filename, String fileType) throws AutoException
	{
		file = new FileIO(filename);
		if (fileType == ".prop")
		{
			Properties prop = new Properties();
			a1 = parseProperties(prop);
		}
		else
		{
			a1 = file.buildAutoObject(filename, fileType);
		}
		
		// Add the Automobile to the linked hash map
		addAuto(a1);
	}
				
	// printAuto - Search and print the properties of a given Automobile make and model
	public void printAuto(String make, String modelName)
	{
		a1.printData();
	}
		
	// updateOptionSetName - This function searches the Model for a given OptionSet and sets the name of OptionSet to newName.
	public void updateOptionSetName(String modelName, String optionSetName, String newName)
	{
		a1.updateOptSetName(optionSetName, newName);
		file.updateFile(a1);
	}
				
	// updateOptionPrice - This function searches the Model for a given OptionSet and Option name, and sets the price to newPrice.
	public void updateOptionPrice(String modelName, String OptionSetName, String option, float newPrice)
	{
		a1.updateOptPrice(option, newPrice);
		file.updateFile(a1);
	}
	
	// buildBadAutoFromFile - Build an Automobile object given a file with incorrect formatting or bad data
	public void buildAutoFromBadFile(String filename, String fileType) throws AutoException
	{
		file = new FileIO(filename);
		a1 = file.buildAutoObject(filename, fileType);
	}
	
	// printLHM - Print all elements of the linked hash map
	public void printLHashMap()
	{
		autoLHM.printAll();
	}
	
	// removeEntry - Remove an entry from the linked hash map
	public void removeEntry(String key)
	{
		autoLHM.delete(key);
	}
	
	// setOptionChoice
	public void setOptionChoice()
	{
		String choice = "";
		Scanner inp = new Scanner(System.in);
		
		for (int i = 0; i < a1.getOptSetLength(); i++)
		{
			System.out.print("Select option for " + a1.getOptSetName(i) + ":\n");
			for (int i2 = 0; i2 < a1.getOptLength(i); i2++)
			{
				System.out.println("   " + a1.getOptName(i, i2));
				if (i2 == a1.getOptLength(i)-1)
				{
					System.out.print("\nSelection: ");
				}
			}
			choice = inp.nextLine();
			a1.setOptionChoice(a1.getOptSetName(i), choice);
			System.out.println("Price: $" + a1.getOptionChoicePrice(a1.getOptSetName(i)));
			System.out.println();
		}
	}
	
	// printTotalOptionPrice
	public void printTotalOptionPrice()
	{
		System.out.println("Total price with all your options: $" + a1.getTotalPrice());
	}
	
	// editThread
	public void editThread(String modelName, int operation, String[] args, int sleepDuration)
	{	
		EditOptions editOpts = new EditOptions(modelName, operation, args, sleepDuration);
	}
	
	// getAutoLHM - Return an Automobile from the linked hash map
	public LHMAuto<String, Automobile> getAutoLHM()
	{
		return autoLHM;
	}
	
	// connect
	public void connect(String strHost, int port)
	{
		DefaultSocketClient defSocketClient = null;
		defSocketClient = new DefaultSocketClient(strHost, port);
		
		defSocketClient.start();
	}
	
	
	// parseProperties - Parse a Properties object to build an Automobile
	public Automobile parseProperties(Object prop)
	{
		Automobile auto = null;
		file = new FileIO();
		auto = file.handleProperties(prop);
		
		return auto;
	}
	
	// parseText - Parse a StringBuffer object to build an Automobile
	public Automobile parseText(Object txt)
	{
		Automobile auto = null;
		file = new FileIO();
		auto = file.handleText(txt);
		
		return auto;
	}
	
	// addAuto - Add an Automobile to the linked hash map
	public void addAuto(Automobile a1)
	{
		autoLHM.add(a1.getMake()+ " " + a1.getModel(), a1);
		autoLHM.printAll();
	}
	
	// getAllModels - Return all elements of the linked hash map as a String
	public String getAllModels()
	{
		return autoLHM.getAllModelsAsString();
	}
}