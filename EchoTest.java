/******************************************************************
		Christopher D. Cavello
		May 21, 1997
		ee 701
		project 2

 EchoTest.java 
 javasoft /nav/read/Tutorial/networking/sockets/readingWriting.html
*******************************************************************/


/******************************************************************
	This connects to port 13 of those UNIX machines which allow
	the date/time service
*******************************************************************/

import java.io.*;
import java.net.*;

public class EchoTest {
public static void main(String[] args) {
Socket echoSocket = null;
DataOutputStream os = null;
DataInputStream is = null;
DataInputStream stdIn = new DataInputStream(System.in);

try 
{
	echoSocket = new Socket("er4hp62", 13);
	os = new DataOutputStream(echoSocket.getOutputStream());
	is = new DataInputStream(echoSocket.getInputStream());
} 
catch (UnknownHostException e) 
{
	System.err.println("Don't know about host: er4hp62");
} 
catch (IOException e) 
{
	System.err.println("Couldn't get I/O for the connection to: er4hp62");
}

if (echoSocket != null && os != null && is != null) 
{
	try 
	{
	String userInput;
	while ((userInput = stdIn.readLine()) != null) 
	{
    os.writeBytes(userInput);
    os.writeByte('\n');
    System.out.println("Time is " + is.readLine());
	}
	os.close();
	is.close();
	echoSocket.close();
} 
catch (IOException e) 
{
	System.err.println("I/O failed on the connection to: er4hp62");
}

} /* end of IF */
} /* end of "public static void main(String[] args) "  */
} /* end of  "public class EchoTest"      */
