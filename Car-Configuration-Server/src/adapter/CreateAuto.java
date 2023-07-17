/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package adapter;

import exception.AutoException;

// Contains methods to build Automobile objects
public interface CreateAuto {
	public void buildAuto(String filename, String fileType) throws AutoException;
	public void printAuto(String make, String modelName);
}