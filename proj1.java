/******************************************************************
		Christopher D. Cavello
		May 21, 1997
		ee 701
		project 2
*******************************************************************/

/*
 *  server: add your code serverthread
 */
import java.util.*;
import java.net.*;
import java.io.*;

class server 
{
public static void main(String[] args) 
{
	ServerSocket serverSocket = null;
	boolean listening = true;
	try 
	{       
    		serverSocket = new ServerSocket(4567);
	} 
	catch (IOException e) 
	{
  		System.err.println("Could not listen on port: " + 4567 + ", " + e.getMessage());
  		System.exit(1);
	}

	while (listening) 
	{       
   		Socket clientSocket = null;
   		try 
   		{
      			clientSocket = serverSocket.accept();
   		} 
   		catch (IOException e) 
   		{
      			System.err.println("Accept failed: " + 4567 + ", " + e.getMessage());
      			continue;
   		}

//
// add your code
// write the server thread code for handling the request-for-time packet and
// return time-of-day packet to the client
//

   		new serverthread(clientSocket).start();
	}
	try 
	{
   		serverSocket.close();
	} 
	catch (IOException e) 
	{
  		System.err.println("Could not close server socket." + e.getMessage());
	}
}

}

/*
 * client:   add your code
 */
import java.util.*;     //utility for date, random functions
import java.io.*;
import java.net.*;

public class Client {
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
	clientSocket = new Socket("cdcpc", 4567);    
	os = new PrintStream(clientSocket.getOutputStream());
	is = new DataInputStream(clientSocket.getInputStream());
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
			os.writeByte('\n');
    			System.out.println("Time is " + is.readLine());
			os.writeByte('\n');
		}
	os.close();
	is.close();
	clientSocket.close();
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

  
