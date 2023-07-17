/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package driver;

import adapter.BuildAuto;
import server.AutoServer;

public class Driver5 {
	public static void main(String[] args)
	{
		AutoServer as = new BuildAuto();
		as.serve(4444);
	}
}