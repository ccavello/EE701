/******************************************************************
		Christopher D. Cavello
		May 21, 1997
		ee 701
		project 2
*******************************************************************/

/*
 * client:   add your code
 */
import java.util.*;     //utility for date, random functions
import java.io.*;
import java.net.*;

public class p1clnt {
public static void main(String[] args) {

Socket clientSocket = null;    // socket declaration 
PrintStream os = null;  // write to socket    
DataInputStream is = null;     // read from socket  

//
// add timer code   
// make the client a thread, sleep for a period of T, then wake up 
//

// create socket
try 
{
	// replace the machine name 
	clientSocket = new Socket("cdcpc", 13);  // open socket    
	os = new PrintStream(clientSocket.getOutputStream()); //open output stream
	is = new DataInputStream(clientSocket.getInputStream());//open input stream
	DataInputStream stdIn = new DataInputStream(System.in);
} 
catch (UnknownHostException e) 
{
	System.err.println("Don't know about host: cdcpc");
} 
catch (IOException e) 
{
	System.err.println("Couldn't get I/O for the connection to: cdcpc");
}

if (clientSocket != null && os != null && is != null) 
{
	try 
	{
		String fromServer;
		// define the packet format: header + message body
		while ((fromServer = is.readLine()) != null) 
		{
			// code for sending request for time packet
			// code parsing the packet from server
			// code for printing time of day
    			/* below 3 lines CDC 5-27-97   */
			//System.out.println("\n");
    			System.out.println("Time is " + is.readLine());
			//System.out.println("\n");
		}
	os.close();  //close stream
	is.close();  //close stream
	clientSocket.close();		//close socket
	} 
    	catch (UnknownHostException e) 
	{
		System.err.println("Trying to connect to unknown host: " + e);
	} 
	catch (IOException e) 
	{
		System.err.println("IOException:  " + e);
	}
}
    
} /* end of public static void main(String[] args) */
} /* end of public class Client  */

  
