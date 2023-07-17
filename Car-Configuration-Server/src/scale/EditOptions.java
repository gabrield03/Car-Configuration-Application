/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package scale;

import adapter.ProxyAuto;
import model.*;

public class EditOptions extends ProxyAuto implements Runnable {
	private Thread t;
	private Automobile auto;
	private boolean DEBUG = true;
	private String modelName;
	private String[] args;
	private int operation;
	private int sleepDuration;
	
	public EditOptions() {}
	
	public EditOptions(String modelName, int operation, String[] args, int sleepDuration)
	{
		this.modelName = modelName;
		this.operation = operation;
		this.args = args;
		this.t = new Thread(this);
		this.auto = this.getAutoLHM().get(modelName);
		this.sleepDuration = sleepDuration;
		
		t.start();
	}
	
	// run - calls ops
	public void run()
	{
		ops();
	}
	
	// ops - Implements a switch statement to change an option and prints the changes (or not) to screen
	public void ops() {
		EditOptionsHelper helper = new EditOptionsHelper(); 
		boolean wasChanged = false;
		switch(operation) {
		
			// Calls non-synched method in helper class - Update the option name from X to Y
			case 0:
				wasChanged = helper.changeColor(auto, args);
				break;
				
			// Calls non-synched method in helper class - Update the option name from X to Z
			case 1:
				wasChanged = helper.changeColor(auto, args);
				break;
			
			// Calls synched method in helper class - Update the option name from X to Y
			case 2:
				wasChanged = helper.changeColorSynch(auto, args, sleepDuration);
				break;
				
			// Calls synched method in helper class - Update the option name from X to Z
			case 3:
				wasChanged = helper.changeColorSynch(auto, args, sleepDuration);
				break;
				
			default:
				break;
		}
		
		// Each thread prints whether it changed the option or not
		if (wasChanged)
		{
			System.out.printf("%s:\n%s to %s -- successful\n\n", t.getName(), getArgs(0), getArgs(1));
		}
		else
		{
			System.out.printf("%s:\n%s to %s -- not successful\n\n", t.getName(), getArgs(0), getArgs(1));
		}
	}

	public Thread getT()
	{
		return t;
	}

	public void setT(Thread t)
	{
		this.t = t;
	}

	public Automobile getAuto()
	{
		return auto;
	}

	public void setAuto(Automobile auto)
	{
		this.auto = auto;
	}

	public boolean getDEBUG()
	{
		return DEBUG;
	}

	public void setDEBUG(boolean DEBUG)
	{
		this.DEBUG = DEBUG;
	}

	public String getModelName()
	{
		return modelName;
	}

	public void setModelName(String modelName)
	{
		this.modelName = modelName;
	}

	public String[] getArgsArr()
	{
		return args;
	}

	public void setArgsArr(String[] args)
	{
		this.args = args;
	}

	public String getArgs(int n)
	{
		return args[n];
	}
	
	public void setArgs(int n, String newArg)
	{
		this.args[n] = newArg;
	}
	
	public int getOperation()
	{
		return operation;
	}

	public void setOperation(int operation)
	{
		this.operation = operation;
	}

	public int getSleepDuration()
	{
		return sleepDuration;
	}

	public void setSleepDuration(int sleepDuration)
	{
		this.sleepDuration = sleepDuration;
	}
}