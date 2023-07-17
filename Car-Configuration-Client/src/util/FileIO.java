/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;
import adapter.Debuggable;
import exception.AutoException;
import exception.AutoException.ErrorList;
import java.io.IOException;
import model.*;

public class FileIO implements Debuggable {
	private String fname;
	
	public FileIO() { }
	
	public FileIO(String fname)
	{
		this.fname = fname;
	}
	
	// Builds the Automobile object with OptionSets and Options
	public Automobile buildAutoObject(String fname, String fileType) throws AutoException
	{
		int totalOptions = 0, currOptionSet = 0, lineCount = 0;
		
		// Find how many option sets in the text file
		int totalOptionSets = countOptions();
		
		// Instantiate an Automobile object
		Automobile auto1 = new Automobile(null, null, 2000, 0, totalOptionSets);
		
		if (fileType.equals(".prop"))
		{
			Properties p = new Properties();
			auto1 = handleProperties(p);
		}	
		else
		{
			try
			{
				FileReader file = new FileReader(fname);
				BufferedReader buff = new BufferedReader(file);
				boolean eof = false;
				
				while (!eof)
				{
					lineCount++;
					String line = buff.readLine();
					if (line == null)
						eof = true;
					else
					{
						// Find the number of options in an option set
						// Build the option sets and assign options
						totalOptions = countTokens(line, lineCount);
						auto1 = buildOptSet(auto1, currOptionSet, line, totalOptions, lineCount);
						if (lineCount > 4)
						{
							currOptionSet++;
						}
					}
				}
				buff.close();
			}
			catch (IOException e)
			{
				System.out.println("IO Error - " + e);
			}
		}

		return auto1;
	}
	
	// buildOptSet - Builds each OptionSet by line from file and assigns the Options to the OptionSet 
	public Automobile buildOptSet(Automobile a1, int optSetIndex, String line, int numOptions, int lineCount) throws AutoException
	{
		int currTokenCount = 0, currOptionNum = 0, lastUsedIndex = 0;
		float tempPrice = 0f;
		String tempLine = line, tempOptionName = "";
		AutoException autoException = null;
		boolean hadToFix = false;
		
		for (int i = 0; i < line.length(); i++)
		{
			// Items in the file are delimited by ','
			if (line.charAt(i) == ',')
			{
				// First line is the make of the automobile
				if (lineCount == 1)
				{
					// Try to parse the first line of the file
					// Throws a StringIndexOutOfBoundsException if make is missing
					try
					{
						String make = line.substring(i+2, line.length()-1);
						a1.setMake(make);
					}
					// Make is missing, StringIndexOutOfBoundsException
					catch(StringIndexOutOfBoundsException e)
					{
						autoException = new AutoException(1, "No make");
						ErrorList error = ErrorList.NOMAKE;
						String fixMake = autoException.fix(error, -1, null, -1);
						a1.setMake(fixMake);
					}
					finally
					{
						i = line.length();
					}
				}
				
				// Second line is model of the automobile
				else if (lineCount == 2)
				{
					// Try to parse the first line of the file
					// Throws a StringIndexOutOfBoundsException if model is missing
					try
					{
						String model = line.substring(i+2, line.length()-1);
						a1.setModel(model);
					}
					catch(StringIndexOutOfBoundsException e)
					{
						autoException = new AutoException(2, "No model");
						ErrorList error = ErrorList.NOMODEL;
						String fixModel = autoException.fix(error, -1, null, -1);
						a1.setModel(fixModel);
					}
					finally
					{
						i = line.length();
					}
				}
				
				// Third line is the base price of the automobile
				else if (lineCount == 3)
				{
					// Try to parse the first line of the file
					// Throws a StringIndexOutOfBoundsException if base price is missing
					try
					{
						String basePrice = line.substring(i+3, line.length()-1);
						a1.setBasePrice(Double.parseDouble(basePrice));
					}
					catch(StringIndexOutOfBoundsException e)
					{
						autoException = new AutoException(3, "No base price");
						ErrorList error = ErrorList.NOBASEPRICE;
						String fixBasePrice = autoException.fix(error, -1, null, -1);
						a1.setBasePrice(Double.parseDouble(fixBasePrice));
					}
					finally
					{
						i = line.length();
					}
				}
				
				// Fourth line is the year of the automobile
				else if (lineCount == 4)
				{
					String year = line.substring(i+2, line.length()-1);
					a1.setYear(Integer.parseInt(year));
					i = line.length();
				}
					
				else
				{
					// The 1st item is the option set name
					// Assign the name of the option set
					if (currTokenCount == 0)
					{
						String optSetName = line.substring(0, i);
						optSetName = optSetName.trim();
						
						// Set an empty Option Set name to null to catch a missing Option Set
						if(optSetName.length() == 0)
						{
							optSetName = null;
						}
						
						// Comparing the null value will throw a NullPointerException
						try
						{
							optSetName.equals(optSetName);
							
							// test
							OptionSet tempOS = new OptionSet(optSetName, numOptions);
										
							a1.setOptSetName(optSetIndex, tempOS);
							
						}
						catch(NullPointerException e)
						{
							autoException = new AutoException(4, "No Option Set");
							ErrorList error = ErrorList.NOOPTIONSET;
							String fixOptionSetName = autoException.fix(error, optSetIndex, null, -1);
							
							// test
							OptionSet tempOS = new OptionSet(fixOptionSetName, numOptions);
							a1.setOptSetName(optSetIndex, tempOS);
							//a1.setOptSetName(optSetIndex, fixOptionSetName);
						}
						finally
						{
							lastUsedIndex = i;
						}
					}
					
					// The 2nd item is the number of options in this line (already taken into account)
					// Move the index
					else if (currTokenCount == 1)
						lastUsedIndex = i;
					
					// Other items are options and their prices
					// Find price and name of the options
					else
					{
						tempLine = line.substring(lastUsedIndex+3, i);
						
						// 1st item is the price and 2nd item is option name, delimited by ' '
						for (int i2 = 0; i2 < tempLine.length(); i2++)
						{
							hadToFix = false;
							if (tempLine.charAt(i2) == ' ')
							{
								// Try to convert the String to a float
								// Throws a NumberFormatException if the Option's price is missing
								try 
								{
									tempPrice = Float.parseFloat(tempLine.substring(0,i2));
								}
								catch(NumberFormatException e)
								{
									autoException = new AutoException(5, "No Option price");
									ErrorList error = ErrorList.NOOPTIONPRICE;
									tempPrice = Float.parseFloat(autoException.fix(error, -1, a1.getOptSetName(lineCount-4), -1));
									hadToFix = true;
								}
								
								if (hadToFix)
								{
									tempOptionName = tempLine.substring(0,tempLine.length()).trim();
								}
								else
								{
									tempOptionName = tempLine.substring(i2+1).trim();
								}
								
								// If the Option name is empty, set it to null
								if(tempOptionName.length() == 0)
								{
									tempOptionName = null;
								}
								
								// Comparing the null value will throw a NullPointerException
								try
								{
									tempOptionName.equals(tempOptionName);
								}
								catch(NullPointerException e)
								{
									autoException = new AutoException(6, "No Option");
									ErrorList error = ErrorList.NOOPTION;
									tempOptionName = autoException.fix(error, optSetIndex, a1.getOptSetName(optSetIndex), tempPrice);
								}
								finally
								{
									i2 = tempLine.length();
								}
							}
						}
						
						// Set the option set option values
						a1.setOneOptSetOpt(optSetIndex, currOptionNum, tempOptionName, tempPrice);
						lastUsedIndex = i;
						currOptionNum++;
					}
					currTokenCount++;
				}
			}
		}
		
		return a1;
	}
	
	// countOptions - Returns the number of lines -4 in a text file. Represents the number of OptionSets
	// Skip the first four lines - make, model, base price, and year
	public int countOptions()
	{
		int numOptionSets = 0;
		
		// Read the file and count the number of lines
		try {
			FileReader file = new FileReader(fname);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			
			while (!eof)
			{
				String line = buff.readLine();
				if (line == null)
					eof = true;
				else
					numOptionSets++;
			}
			numOptionSets -= 4;
		}
		catch(IOException e)
		{
			System.out.println("IO error - " + e);
		}
		
		return numOptionSets;
	}
	
	// countTokens - Returns the number of tokens (Options) on a line
	public int countTokens(String line, int lineCount)
	{
		int numTokens = 0, firstIndex = 0, lastIndex = 0;
		boolean foundIndex = false;	
		
		// Make of the automobile
		if (lineCount > 4)
		{
			// Iterate over the given line. Find the first number
			// Be aware that there may be numbers greater than 9 (can't just take the first digit found)
			for (int i = 0; i < line.length(); i++)
			{
				// Find the first number on the line
				if (Character.isDigit(line.charAt(i)) && (!foundIndex))
				{
					firstIndex = i;
					foundIndex = true;
				}
				// Find the last index+1 of the number in the line
				else if (line.charAt(i) == ',' && foundIndex)
				{
					lastIndex = i;
					i = line.length();
				}
			}
			// Convert the numeric string to int
			numTokens = Integer.parseInt(line.substring(firstIndex, lastIndex));
		}
		
		return numTokens;
	}
	
	// serializeData - Serialize and write to file an Automotive object
	public String serializeData(Automobile a1)
	{
		// Create the serialized filename
		String fname = a1.getMake() + " " + a1.getModel() + ".txt";
		String serializedFname = "P:\\Eclipse-Java\\CIS 35B\\Assignments\\serializedAutomotives\\" + fname;
		
		// Serialize the Automotive object
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(serializedFname));
			out.writeObject(a1);
			out.close();
		}
		catch (IOException e)
		{
			System.out.println("IO Error - " + e);
		}
		
		return serializedFname;
	}
	
	// deserializeData - Returns an Automotive object after deserializing a given file
	public Automobile deserializeData(String serializedFname)
	{
		Automobile newA1;
		
		// Deserialize the file
		try
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(serializedFname));
			newA1 = new Automobile();
			newA1 = (Automobile) in.readObject();
			in.close();
		}
		catch (Exception e)
		{
			System.out.println("Deserialization error - " + e);
			newA1 = null;
		}
		
		return newA1;
	}
	
	// searchForAuto - Searches for a file by make and model 
	public Automobile searchForAuto(String make, String modelName)
	{
		Automobile a1 = new Automobile();
		String serializedFile = "P:\\Eclipse-Java\\CIS 35B\\Assignments\\serializedAutomotives\\" + make + " " + modelName + ".txt";
		
		a1 = deserializeData(serializedFile);
		
		return a1;
	}
	
	// updateFile - Updates the old serialized file with the new data
	public void updateFile(Automobile a1)
	{
		// Can change filename to see the changes that are made - make+model+updated.txt...etc
		String filename = "P:\\Eclipse-Java\\CIS 35B\\Assignments\\AutoProperties\\" + a1.getMake() + a1.getModel() + ".txt";
		
		try
		{
			FileWriter file = new FileWriter(filename);
			BufferedWriter buff = new BufferedWriter(file);
			buff.write("Make, " + a1.getMake() + ",\n");
			buff.write("Model, " + a1.getModel() + ",\n");
			buff.write("Base Price, $" + (int)a1.getBasePrice() + ",\n");
			buff.write("Year, " + a1.getYear() + ",\n");
			
			for (int i = 0; i < a1.getOptSetLength(); i++)
			{
				buff.write(a1.getOptSetName(i) + ", " + a1.getOptLength(i) + ", ");
				for (int i2 = 0 ; i2 < a1.getOptLength(i); i2++)
				{
					int tempPrice = (int)a1.getOptPrice(i, i2);
					buff.write("$" + tempPrice + " " + a1.getOptName(i, i2) + ", ");
				}
				if (i != a1.getOptSetLength()-1)
				{
					buff.newLine();
				}
			}
			buff.close();
		}
		catch (IOException e)
		{
			System.out.println("Error - " + e);
		}
		
		String serializedFile = serializeData(a1);
	}
	
	// Assignment 5 additions
	// handleProperties - Parse a Properties object and build an Automobile object
	public Automobile handleProperties(Object prop)
	{
		Automobile auto = null;
		Properties props = (Properties) prop;
		
		int totalOptionSets = countPropertyOptSets(props);

		String carMake = props.getProperty("CarMake");
		String carModel = props.getProperty("CarModel");
		double carBasePrice = Double.parseDouble(props.getProperty("BasePrice"));
		int carYear = Integer.parseInt(props.getProperty("Year"));
		
		auto = new Automobile(carMake, carModel, carYear, carBasePrice, totalOptionSets);
		
		for (int i = 1; i <= totalOptionSets; i++)
		{
			int numOptions = countPropertyOptions(props, i); 
			String tempOptSetName = "Option" + i;
			OptionSet tempOS = new OptionSet(props.getProperty(tempOptSetName), numOptions);
			
			auto.setOptSetName(i, tempOS);
			
			for (int i2 = 1; i2 <= numOptions; i2++)
			{
				char tempChar = 'a';
				tempChar += (i2-1);
				
				String findOptionName = "OptionValue" + i + tempChar;
				String findOptionPrice = findOptionName + "Price";
				String tempOptionName = props.getProperty(findOptionName);
				float tempOptionPrice = Float.parseFloat(props.getProperty(findOptionPrice));
				
				auto.setOneOptSetOpt(i-1, i2, tempOptionName, tempOptionPrice);
			}
		}
		
		return auto;
	}
	
	// countPropertyOptSets - Return the number OptionSets in the Properties object
	public int countPropertyOptSets(Properties props)
	{
		int count = 1;
		boolean moreToCount = true;
		String numOptSets = "";
		
		do
		{
			String optionSetFinder = "Option" + count;
			numOptSets = props.getProperty(optionSetFinder);
			
			if (numOptSets != null)
			{
				count++;
			}
			else
			{
				count--;
				moreToCount = false;
			}
		
		} while (moreToCount);
		return count;
	}
	
	// countPropertyOptions - Return the number of Options in the Properties object
	public int countPropertyOptions(Properties props, int currOptSet)
	{
		char myChar = 'a';
		int count = 1;
		boolean moreToCount = true;
		String numOptions = "";
		
		do
		{
			String optionFinder = "OptionValue" + currOptSet + myChar;
			numOptions = props.getProperty(optionFinder);
			
			if (numOptions != null)
			{
				myChar++;
				count++;
			}
			else
			{
				count--;
				moreToCount = false;
			}
		
		} while (moreToCount);
		return count;
	}
	
	// handleText - Parse a StringBuffer object and build an Automobile object
	public Automobile handleText(Object txt)
	{
		Automobile auto = null;
		StringBuffer carTxt = (StringBuffer) txt;
		
		// Get the make, model, base price, and year of the car
		String carMake = carTxt.substring(carTxt.indexOf("=") + 1, carTxt.indexOf("\n"));
		carTxt = carTxt.delete(0, carTxt.indexOf("\n")+1);
		
		String carModel = carTxt.substring(carTxt.indexOf("=") + 1, carTxt.indexOf("\n"));
		carTxt = carTxt.delete(0, carTxt.indexOf("\n")+1);
		
		double carBasePrice = Double.parseDouble(carTxt.substring(carTxt.indexOf("=") + 1, carTxt.indexOf("\n")));
		carTxt = carTxt.delete(0, carTxt.indexOf("\n")+1);
		
		int carYear = Integer.parseInt(carTxt.substring(carTxt.indexOf("=") + 1, carTxt.indexOf("\n")));
		carTxt = carTxt.delete(0, carTxt.indexOf("\n")+2);
		
		int totalOptSets = countTextOptSets(carTxt);
		
		auto = new Automobile(carMake, carModel, carYear, carBasePrice, totalOptSets);
		
		// Build the Automobile object by adding Options to OptionSets
		for (int i = 1; i <= totalOptSets; i++)
		{
			int numOptions = countTextOptions(carTxt, i);
			
			String findOptSet = "Option" + i + "=";
			String tempOptSetName = carTxt.substring(carTxt.indexOf(findOptSet) + 8, carTxt.indexOf("\n"));
			carTxt = carTxt.delete(0, carTxt.indexOf("\n")+1);
			
			OptionSet tempOS = new OptionSet(tempOptSetName, numOptions);
			auto.setOptSetName(i, tempOS);
			
			for (int i2 = 1; i2 <=numOptions; i2++)
			{
				char tempChar = 'a';
				tempChar += (i2-1);
				Float tempOptionPrice = 0f;
				
				String findOptionName = "OptionValue" + i + tempChar + "=";
				String findOptionPrice = "OptionValue" + i + tempChar + "Price=";
				String tempOptionName = carTxt.substring(carTxt.indexOf(findOptionName) + 14, carTxt.indexOf("\n"));
				carTxt = carTxt.delete(0, carTxt.indexOf("\n")+1);
				
				if (i2 == numOptions && i == totalOptSets)
				{
					tempOptionPrice = Float.parseFloat(carTxt.substring(carTxt.indexOf(findOptionPrice) + 19));
				}
				else
				{
					tempOptionPrice = Float.parseFloat(carTxt.substring(carTxt.indexOf(findOptionPrice) + 19, carTxt.indexOf("\n")));
				}
				carTxt = carTxt.delete(0, carTxt.indexOf("\n")+1);
				
				while (carTxt.substring(0,1).equals("\n")) 
				{
					carTxt = carTxt.delete(0, 1);
				}
				
				auto.setOneOptSetOpt(i-1, i2, tempOptionName, tempOptionPrice);
			}
		}
		
		return auto;
	}
	
	// countTextOptSets - Return the number OptionSets in the StringBuffer object
	public int countTextOptSets(StringBuffer txt)
	{
		int optCount = 1;
		boolean moreToCount = true;
		
		do
		{
			String findOptionSets = "Option" + optCount;
			if (txt.indexOf(findOptionSets) != -1) 
			{
				optCount++;
			}
			else
			{
				optCount--;
				moreToCount = false;
			}
		} while(moreToCount);
		
		return optCount;
	}
	
	// countTextOptions - Return the number Options in the StringBuffer object
	public int countTextOptions(StringBuffer txt, int currOptSet)
	{
		char myChar = 'a';
		int count = 1;
		boolean moreToCount = true;
		
		do
		{
			String findOptions = "OptionValue" + currOptSet + myChar;
			if (txt.indexOf(findOptions) != -1) 
			{
				count++;
				myChar++;
			}
			else
			{
				count--;
				moreToCount = false;
			}
		} while (moreToCount);
		return count;
	}
}