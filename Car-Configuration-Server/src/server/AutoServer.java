/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package server;

import model.Automobile;

// Contains methods to implement the server
public interface AutoServer {
	public void serve(int port);
	
	public Automobile parseProperties(Object prop);
	
	public Automobile parseText(Object txt);
	
	public void addAuto(Automobile a1);
	
	public Automobile getSelectedAuto(Object obj);
}