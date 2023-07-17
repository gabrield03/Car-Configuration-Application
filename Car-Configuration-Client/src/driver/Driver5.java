/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package driver;

import adapter.BuildAuto;
import client.AutoClient;

public class Driver5 {
	public static void main(String[] args)
	{
		AutoClient ac = new BuildAuto();
		ac.connect("127.0.0.1", 4444);
	}
}