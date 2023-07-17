/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package client;

import java.io.*;
import java.util.*;
import adapter.Debuggable;

public class CarModelOptionsIO implements Debuggable {
	public CarModelOptionsIO() {}

	// Load a Properties file to an input stream
	public Object loadPropsFile(String fname) {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(fname));
		}
		catch (FileNotFoundException e) {
			System.err.println("Error in file directory ... ");
			System.exit(1);
		}
		catch (IOException e) {
			System.err.println("Error reading file from directory ... ");
			System.exit(1);
		}

		return props;
	}

	// Load a text file to a String Buffer
	public Object loadTextFile(String fname) {
		StringBuffer sbuff = new StringBuffer();
		try {
			BufferedReader buff = new BufferedReader(new FileReader(fname));
			boolean eof = false;
			int counter = 0;
			while (!eof) {
				String line = buff.readLine();
				if (line == null)
					eof = true;
				else {
					if (counter == 0)
						sbuff.append(line);
					else
						sbuff.append("\n" + line);
				}
				counter++;
			}
			buff.close();
		}
		catch (FileNotFoundException e) {
			System.err.println("Error in file directory ... ");
			System.exit(1);
		}
		catch (IOException e) {
			System.err.println("Error reading file from directory ... ");
			System.exit(1);
		}

		return sbuff;
	}
}