/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package adapter;

// Contains a method related to multithreading
public interface EditAuto {
	public void editThread(String modelName, int operation, String[] args, int sleepDuration);
}