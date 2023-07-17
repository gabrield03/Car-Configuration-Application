/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package scale;

import model.Automobile;

public class EditOptionsHelper {
	public EditOptionsHelper() {}

	public boolean changeColor(Automobile auto, String[] args)
	{
		// added a boolean return value to check if the option was changed
		boolean wasChanged = false;
		String oldVal = args[0];
		String newVal = args[1];
		
		// Loop to find the desired option to change and make the change
		for (int i = 0; i < auto.getOptLength(0); i++)
		{
			if (auto.getOptName(0, i).equals(oldVal))
			{
				auto.updateOptSetOpt(0, i, newVal);
				wasChanged = true;
			}
		}
		
		return wasChanged;
	}
	
	public synchronized boolean changeColorSynch(Automobile auto, String[] args, int sleepDuration)
	{
		boolean wasChanged = false;
		String oldVal = args[0];
		String newVal = args[1];
		
		// Note: Inducing sleep prior to changing would yield both threads successful because the 
		// match would have already been found before going to sleep. Then, it would replace the 
		// index of the old value with the new value. To route this, we would have to check (again)
		// if the old value was still present: if (auto.getOptName(0, i).equals(oldVal)) before
		// making the change. That seems redundant so I left the sleep method outside the for loop.
		
		// Find the option set option's index
		// For this program we know we are changing the optionSet "color" (index 0 of opt length)
		try
		{
			Thread.sleep(sleepDuration);
			for (int i = 0; i < auto.getOptLength(0); i++)
			{
				if (auto.getOptName(0, i).equals(oldVal))
				{
					auto.updateOptSetOpt(0, i, newVal);
					wasChanged = true;
				}
			}
		} catch (InterruptedException ex)
		{
			ex.printStackTrace();
		}
		
		return wasChanged;
	}
}