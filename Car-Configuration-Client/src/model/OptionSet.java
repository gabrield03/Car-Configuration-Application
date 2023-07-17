/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package model;

import java.io.Serializable;
import java.util.ArrayList;

public class OptionSet implements Serializable{
	private String optSetName;
	private ArrayList<Option> opt;
	private Option chosenOption;
	
	public OptionSet()
	{
		optSetName = null;
		opt = new ArrayList<Option>();
		chosenOption = new Option();
	}
	
	public OptionSet(String optSetName, int size)
	{
		this.optSetName = optSetName;
		opt = new ArrayList<Option>();
		chosenOption = new Option();
	}
	
	// getSetName - Returns the option set name
	protected String getOptSetName()
	{
		return optSetName;
	}
	
	// setSetName - Sets the option set name based on a given name
	protected void setOptSetName(String newOptSetName)
	{
		optSetName = newOptSetName;
	}
	
	// getOneOpt - Returns one option in the array based on a given index
	protected Option getOneOpt(int index)
	{
		return opt.get(index);
	}
	
	// getOpt - Returns the option array
	protected ArrayList<Option> getOpt()
	{
		return opt;
	}
	
	// setOneOpt - Sets one option in the array based on a given index
	protected void setOneOpt(Option newOpt, int index)
	{
		opt.set(index, newOpt);
	}
	
	// setOpt - Sets an option array based on a given option array
	protected void setOpt(ArrayList<Option> newOptArr)
	{
		opt.clear();
		opt = newOptArr;
	}
	
	// getOptLength - Returns the size of the option array
	protected int getOptLength()
	{
		return opt.size();
	}
	
	// buildOption - Builds an option based on a given index, name, and price
	protected void buildOption(int index, String name, float price)
	{
		Option tempOpt = new Option(name, price);
		opt.add(tempOpt);
	}
	
	// getOptName - Returns the name of an option based on a given index
	protected String getOptName(int index)
	{
		return opt.get(index).getName();
	}
	
	// setOptName - Sets an option name based on a given index
	protected void setOptName(int index, String newOptName)
	{
		opt.get(index).setName(newOptName);
	}
	
	// getOptPrice - Returns the price of an option based on a given index
	protected float getOptPrice(int index)
	{
		return opt.get(index).getPrice();
	}
	
	// setOptPrice - Sets the price of an option based on a given index
	protected void setOptPrice(int index, float newOptPrice)
	{
		opt.get(index).setPrice(newOptPrice);
	}
	
	// setOptSize - Set the number of options in an option set
	protected void setOptSizeArr(int size) {}
	
	// printData - Prints all options in an option set
	protected void printData()
	{
		System.out.printf("\nOptionSet: %s\n", getOptSetName());
		for (int i = 0; i < opt.size(); i++)
			System.out.printf("Option: $%.2f :: %s\n", opt.get(i).getPrice(), opt.get(i).getName());
	}
	
	// printOneOpt - Prints one option in an option set based on a given index
	protected void printOneOpt(int index)
	{
		System.out.printf("\nOption: $%.2f :: %s\n", opt.get(index).getPrice(), opt.get(index).getName());
	}
	
	// getOptionChoice - Returns the option chosen, if any - otherwise returns null
	protected Option getOptionChoice()
	{
		return chosenOption;
	}
	
	// setOptionChoice - Saves the choice inside OptionSet given the name of an option
	protected void setOptionChoice(String optionName)
	{
		chosenOption.setName(optionName);
		for (int i = 0; i < opt.size(); i++)
		{
			if (opt.get(i).getName().equals(optionName))
			{
				float price = opt.get(i).getPrice();
				chosenOption.setPrice(price);
			}
		}
	}
	
	// Option class - Inner class
	public class Option implements Serializable {
		private String name;
		private float price;
		
		// Default constructor - Sets the option's default name and price
		public Option()
		{
			name = null;
			price = 0;
		}
		
		// Overloaded constructor - Sets the option's name and price based on given input
		public Option(String name, float price)
		{
			this.name = name;
			this.price = price;
		}
		
		// getName - Returns the name of the option
		protected String getName()
		{
			return name;
		}
		
		// setName - Sets the name of the option
		protected void setName(String newName)
		{
			name = newName;
		}
		
		// getPrice - Returns the price of the option
		protected float getPrice()
		{
			return price;
		}
		
		// setPrice - Sets the price of the option
		protected void setPrice(float newPrice)
		{
			price = newPrice;
		}
		
		// printData - Prints the option's price and name
		protected void printData()
		{
			System.out.printf("\nOption: $%.2f :: %s\n", getPrice(), getName());
		}
	}
}