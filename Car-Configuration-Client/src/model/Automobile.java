/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package model;

import java.io.Serializable;
import java.util.ArrayList;
import model.OptionSet.Option;

public class Automobile implements Serializable {
	private String make;
	private String model;
	private int year;
	private double basePrice;
	private ArrayList<OptionSet> optionSet;
	private ArrayList<Option> choice;
	
	public Automobile()
	{
		make = "Default";
		model = "Default";
		year = 2000;
		basePrice = 0;
		optionSet = new ArrayList<OptionSet>();
		choice = new ArrayList<Option>();
	}
	
	public Automobile(String make, String model, int year, double basePrice, int size)
	{
		this.make = make;
		this.model = model;
		this.year = year;
		this.basePrice = basePrice;
		optionSet = new ArrayList<OptionSet>();
		choice = new ArrayList<Option>();
	}
	
	// getMake - Returns the make of the automotive
	public String getMake()
	{
		return make;
	}
	
	// setMake - Sets a new make for the automotive
	public void setMake(String newMake)
	{
		make = newMake;
	}
	
	// getModel - Returns the model of the automotive
	public String getModel()
	{
		return model;
	}
	
	// setModel - Sets a new model for the automotive
	public void setModel(String newModel)
	{
		model = newModel;
	}

	// getYear
	public int getYear()
	{
		return year;
	}
	
	// setYear
	public void setYear(int newYear)
	{
		year = newYear;
	}
	
	// getBasePrice - Returns the base price of the automotive
	public double getBasePrice()
	{
		return basePrice;
	}
	
	// setBasePrice - Sets a new base price for the automotive
	public void setBasePrice(double newBasePrice)
	{
		basePrice = newBasePrice;
	}
	
	// getOneOptSet - Returns one option set based on a given index
	public OptionSet getOneOptSet(int index)
	{
		return optionSet.get(index);
	}
	
	// getOptSet - Returns the option set object
	public ArrayList<OptionSet> getOptSet()
	{
		return optionSet;
	}
	
	// getOptSetLength - Returns the size of the option set array
	public int getOptSetLength()
	{
		return optionSet.size();
	}
	
	// getOptLength - Returns the size of the option array based on a given index
	public int getOptLength(int index)
	{
		return optionSet.get(index).getOptLength();
	}
	
	// setOneOptSet - Sets a new option set based on a given index
	public void setOneOptSet(OptionSet newOptionSet, int index)
	{
		optionSet.set(index, newOptionSet);
	}
	
	// setOptSet - Sets the entire option set array
	public void setOptSet(ArrayList<OptionSet> newOptionSet)
	{
		optionSet.clear();
		optionSet = newOptionSet;
	}
	
	// getOptSetName - Returns the name of the option set based on a given index
	public String getOptSetName(int index)
	{
		return optionSet.get(index).getOptSetName();
	}
	
	// setOptSetName - Sets a new option set name based on a given index
	public void setOptSetName(int index, OptionSet newOptSetName)
	{
		optionSet.add(newOptSetName);
	}
	
	// getOptName - Returns the name of an option based on two given indices
	public String getOptName(int index1, int index2)
	{
		return optionSet.get(index1).getOptName(index2);
	}

	// setOptName - Sets a new name for an option based on two given indices
	public void setOptName(int index1, int index2, String newOptName)
	{
		optionSet.get(index1).setOptName(index2, newOptName);
	}
	
	// getOptPrice - Returns the price of an option based on two given indices
	public float getOptPrice(int index1, int index2)
	{
		return optionSet.get(index1).getOptPrice(index2);
	}
	
	// setOptPrice - Sets a new price for an option based on two given indices
	public void setOptPrice(int index1, int index2, float newOptPrice)
	{
		optionSet.get(index1).setOptPrice(index2, newOptPrice);
	}
	
	// setOneOptSetOpt - Sets a new name and price of an option based on two given indices
	public void setOneOptSetOpt(int index1, int index2, String newName, float newPrice)
	{
		optionSet.get(index1).buildOption(index2, newName, newPrice);
	}
	
	// setOptSize - Set the number of options in an option set for a given index
	public void setOptSetOptSize(int index1, int size) {}
	
	// printMake - Prints the make of the automotive
	public void printMake()
	{
		System.out.printf("Make: %s\n", make);
	}
	
	// printModel - Prints the model of the automotive
	public void printModel()
	{
		System.out.printf("Model: %s\n", model);
	}
	
	// printBasePrice - Prints the base price of the automotive
	public void printBasePrice()
	{
		System.out.printf("Base price: $%.2f\n", basePrice);
	}
	
	// printYear - Prints the year of the automobile
	public void printYear()
	{
		System.out.printf("Year: %d", year);
	}
	
	// printMakeModel - Prints both the make and model of the automotive
	public void printMakeModel()
	{
		printMake();
		printModel();
	}
	
	// printOptSet - Prints all option sets on their own line
	public void printOptSet()
	{
		System.out.printf("\n");
		for(int i = 0; i < getOptSetLength(); i++)
			System.out.printf("Option Set %d: %s\n",(i+1), getOptSetName(i));
	}

	// printOneOptSet - Prints one optSet based on a given index
	// Note (index+1) shows optSet[0] as 1 (the first) index
	public void printOneOptSet(int index)
	{
		System.out.printf("\nOption Set %d: %s\n", (index+1), optionSet.get(index).getOptSetName());
	}
	
	// printOneOpt - Print one option based on two given indices
	public void printOneOpt(int index1, int index2)
	{
		System.out.printf("Option: $%.2f : %s\n", optionSet.get(index1).getOptPrice(index2), optionSet.get(index1).getOptName(index2));
	}
	
	// printData - Print the make, model, base price and all option sets and all options for each option set
	public void printData()
	{
		printMakeModel();
		printBasePrice();
		printYear();
		System.out.printf("\n");
		for (int i = 0; i < getOptSetLength(); i++)
		{
			if (getOptSetName(i) == null)
			{
				i++;
			}
			else
			{
				printOneOptSet(i);
				for (int i2 = 0; i2 < optionSet.get(i).getOptLength(); i2++)
				{
					if (getOptName(i, i2) == null)
					{
						i2++;
					}
					else
					{
						printOneOpt(i, i2);
					}
				}
			}
		}
		System.out.printf("\n\n");
	}
	
	// updateOptSetName - Updates an option set name based on a given index
	public void updateOptSetName(int index, String newSetName)
	{
		optionSet.get(index).setOptSetName(newSetName);
	}

	// updateOptSetName - Updates an option set name based on a given name
	public void updateOptSetName(String givenName, String newSetName)
	{
		for (int i = 0; i < getOptSetLength(); i++)
		{
			if (optionSet.get(i).getOptSetName().equals(givenName))
				optionSet.get(i).setOptSetName(newSetName);
		}
	}
	
	// updateOptName - Updates an option name based on a given name
	public void updateOptName(String givenName, String newOptName)
	{
		for (int i = 0; i < getOptSetLength(); i++)
		{
			for (int i2 = 0; i2 < optionSet.get(i).getOptLength(); i2++)
			{
				if (optionSet.get(i).getOptName(i2).equals(givenName))
					optionSet.get(i).setOptName(i2, newOptName);
			}
		}
	}
	
	// updateOptPrice - Updates an option price based on a given name
	public void updateOptPrice(String givenName, float newOptPrice)
	{
		for (int i = 0; i < getOptSetLength(); i++)
		{
			for (int i2 = 0; i2 < optionSet.get(i).getOptLength(); i2++)
			{
				if (optionSet.get(i).getOptName(i2).equals(givenName))
					optionSet.get(i).setOptPrice(i2, newOptPrice);
			}
		}
	}
	
	// deleteOptSet - Deletes an option set (sets name to null) based on a given index
	public void deleteOptSet(int index)
	{
		optionSet.remove(index);
	}
	
	// deleteOptSet - Deletes an option set (sets name to null) based on a given name
	public void deleteOptSet(String givenName)
	{
		for (int i = 0; i < getOptSetLength(); i++)
		{
			if (optionSet.get(i).getOptSetName().equals(givenName))
				optionSet.remove(i);
		}
	}

	// deleteOpt - Deletes an option (sets name to null and price to 0) based on two given indices
	public void deleteOpt(int index1, int index2)
	{
		optionSet.get(index1).setOptName(index2, null);
		optionSet.get(index1).setOptPrice(index2, 0);
	}
	
	// deleteOpt - Deletes an option (sets name to null) based on a given name
	public void deleteOpt(String givenName)
	{
		for (int i = 0; i < getOptSetLength(); i++)
		{
			for (int i2 = 0; i2 < optionSet.get(i).getOptLength(); i2++)
			{
				if (optionSet.get(i).getOptName(i2).equals(givenName))
				{
					optionSet.get(i).setOptName(i2, null);
					optionSet.get(i).setOptPrice(i2, 0);
				}
			}
		}
	}
	
	// getOptionChoice
	public String getOptionChoice(String optSetName)
	{
		String optionChoice = null;
		boolean foundOptionChoice = false;
		
		for (int i = 0; i < optionSet.size(); i++)
		{
			if (optionSet.get(i).getOptSetName().equals(optSetName))
			{
				for(int i2 = 0; i2 < optionSet.get(i).getOptLength(); i2++)
				{
					for (int i3 = 0; i3 < choice.size(); i3++)
					{
						if (choice.get(i3).getName().equals(optionSet.get(i).getOptName(i2)))
						{
							optionChoice = choice.get(i3).getName();
							i3 = choice.size();
							foundOptionChoice = true;
						}
					}
					if (foundOptionChoice)
						i2 = optionSet.get(i).getOptLength();
				}
				if (foundOptionChoice)
					i = optionSet.size();
			}
		}
		return optionChoice;
	}
	
	// getOptionChoicePrice
	public int getOptionChoicePrice(String optSetName)
	{
		int optionPrice = 0;
		for (int i = 0; i < optionSet.size(); i++)
		{
			if (optionSet.get(i).getOptSetName().equals(optSetName))
			{
				for (int i2 = 0; i2 < optionSet.get(i).getOptLength(); i2++)
				{
					for (int i3 = 0; i3 < choice.size(); i3++)
					{
						if (optionSet.get(i).getOptName(i2).equals(choice.get(i3).getName()))
						{
							optionPrice = (int) optionSet.get(i).getOptPrice(i2);
						}
					}
				}
			}
		}
		
		return optionPrice;
	}
	
	// setOptionChoice - User chooses a particular option in an OptionSet
	public void setOptionChoice(String optSetName, String optionName)
	{
		for (int i = 0; i < optionSet.size(); i++)
		{
			if (optionSet.get(i).getOptSetName().equals(optSetName))
			{
				for (int i2 = 0; i2 < optionSet.get(i).getOptLength(); i2++)
				{
					if (optionSet.get(i).getOptName(i2).equals(optionName))
					{
						choice.add(optionSet.get(i).getOneOpt(i2));
					}
				}
			}
		}
	}
	
	// getTotalPrice - Returns the total price of the chosen options
	public int getTotalPrice()
	{
		int total = 0;
		for (int i = 0; i < choice.size(); i++)
		{
			total += choice.get(i).getPrice();
		}
		
		total += getBasePrice();
		return total;
	}
	
	// updateOptSetOpt - replaces a given option set option with a given option
	public void updateOptSetOpt(int index1, int index2, String newOptionName)
	{
		optionSet.get(index1).setOptName(index2, newOptionName);
	}
	
	// addOneOptSet - Adds an OptionSet to the arrayList
	public void addOneOptSet(OptionSet newOptionSet)
	{
		optionSet.add(newOptionSet);
	}
}