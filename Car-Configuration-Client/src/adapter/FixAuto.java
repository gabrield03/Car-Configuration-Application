/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package adapter;

import exception.AutoException;

// Contains a method to display self-healing software
public interface FixAuto {
	public void buildAutoFromBadFile(String fileName, String fileType) throws AutoException;
}