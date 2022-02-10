/******************************************************************
                Christopher D. Cavello
                May 21, 1997
                ee 701
                project 2
*******************************************************************/
/******************************************************************
 This program borrows a lot from the KnockKnockClient and
		the Cubbyhole programs on Javasoft's web page.
	The skeleton is from Prof. Hou's EE701 class.
*******************************************************************/

/*
 * Client 
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class Client extends Thread 
{
  public static void main(String[] args)
  {
    /* make new socket below    */
    Socket clientSocket = null;
    PrintStream os = null;
    DataInputStream is = null;

    try
    {
      clientSocket = new Socket("cdcpc", 4567);
      /*  "cdcpc" is a machine in ".eng.ohio-state.edu"  */
      os = new PrintStream(clientSocket.getOutputStream());
      is = new DataInputStream(clientSocket.getInputStream());
    }
    catch (UnknownHostException e)
    { System.err.println("Don't know about host: cdcpc");}
    catch (IOException e)
    {System.err.println("Couldn't get I/O for the connection to: cdcpc");}
    if (clientSocket != null && os != null && is != null)
    {
      try
      {
				String fromServer,toServer;
				toServer="Time?";  // inquiry to send to Server
				os.println(toServer);//send inquiry to Server
				System.out.println(" Christopher D. Cavello \n EE701 Java Project \n ");

				while ((fromServer = is.readLine()) != null)
				{
	  			while( 1 !=  0)  //  1 is never equal to 0
					{
						int T=(int)(Math.random()*2000)+1000;     
				//							   T=1000 to 3000 milliseconds  
				// http://www.javasoft.com:80/nav/read/
				//		Tutorial/java/threads/synchronization.html  
				// The above web page has code for the 
				//		random numbers in the "Cubbyhole" program  
						sleep(T);		// "sleep" is in the Cubbyhole program too  
						toServer="Time?";
	  				os.println(toServer);
	  				os.flush();
	  				fromServer=is.readLine();
	  				System.out.println(" \n current time: " + fromServer);
					} // end while 1!=0
				}  /* end while */
 				os.close();
  			is.close();
  			clientSocket.close();
      }/* end TRY   */
      /* ERROR TRAPS BELOW HERE   */
			catch (InterruptedException e) 
      {   /* do nothing  */}
      catch (UnknownHostException e)
      {System.err.println("Trying to connect to unknown host: " + e); }
      catch (IOException e)
      {	System.err.println("IOException:  " + e); }
    } /* end IF  */
  }/* end of public static void main(String[] args) */
}/* end of public class Client  */
