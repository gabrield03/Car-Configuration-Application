/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package client;

// Contains a method to connect to the client
public interface AutoClient {
	public void connect(String strHost, int port);
}