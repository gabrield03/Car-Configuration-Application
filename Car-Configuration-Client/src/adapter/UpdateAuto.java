/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package adapter;

//Contains methods to update an Automobile after it has been configured
public interface UpdateAuto {
	public void updateOptionSetName(String modelName, String optionSetName, String newName);
	public void updateOptionPrice(String modelName, String optionSetName, String option, float newPrice);
	public void removeEntry(String key);
}