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