/*
 * Author: Gabriel Larot
 * Date: June 8, 2023
*/

package exception;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;

public class AutoException extends Exception {
	private int errorNum;
	private String errorMsg;
	
	public AutoException() 
	{
		super();
		printError();
	}
	
	public AutoException(int errorNum, String errorMsg) 
	{
		super();
		this.errorNum = errorNum;
		this.errorMsg = errorMsg;
		printError();
		logException();
	}
	
	public int getErrorNum()
	{
		return errorNum;
	}
	
	public void setErrorNum(int newErrorNum)
	{
		errorNum = newErrorNum;
	}
	
	public String getErrorMsg()
	{
		return errorMsg;
	}
	
	public void setErrorMsg(String newErrorMsg)
	{
		errorMsg = newErrorMsg;
	}
	
	public void printError()
	{
		System.out.printf("AutoException [errorNum=%d, errorMsg=%s]\n", errorNum, errorMsg); 
	}
	
	// Logs each exception to a file and notes the error number, error message, and includes a timestamp
	public void logException()
	{
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		try
		{
			FileWriter file = new FileWriter("P:\\Eclipse-Java\\CIS 35B\\Assignments\\Assignment2\\ExceptionLog\\log.txt", true);
			BufferedWriter buff = new BufferedWriter(file);
			buff.write("AutoException [errorNum = " + errorNum + ", errorMsg=" + errorMsg + ", Timestamp=" + timestamp + "]\n");
			buff.close();
		}
		catch (IOException e)
		{
			System.out.printf("Error --- %s", e.getMessage());
		}
	}
	
	// Calls the helper class to fix each type of exception
	public String fix(ErrorList error, int index1, String name, float price)
	{
		Fix1to100 f1 = new Fix1to100();
		String fixedValue = "";
		
		switch(error) {
			case NOMAKE: 
				fixedValue = f1.fix1(ErrorList.NOMAKE);
				break;
			
			case NOMODEL: 
				fixedValue = f1.fix2(ErrorList.NOMODEL);
				break;
			
			case NOBASEPRICE:
				fixedValue = f1.fix3(ErrorList.NOBASEPRICE);
				break;
				
			case NOOPTIONSET: 
				fixedValue = f1.fix4(ErrorList.NOOPTIONSET, index1);
				break;
			
			case NOOPTIONPRICE: 
				fixedValue = f1.fix5(ErrorList.NOOPTIONPRICE, name);
				break;
			
			case NOOPTION:
				fixedValue = f1.fix6(ErrorList.NOOPTION, index1, name, price);
				break;
			
			default: 
				break;
		}
		
		return fixedValue;
	}
	
	// The enum used for the different errors
	public enum ErrorList {
		NOMAKE,
		NOMODEL,
		NOBASEPRICE,
		NOOPTIONSET,
		NOOPTIONPRICE,
		NOOPTION
	}
}