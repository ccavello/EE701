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


class Server 
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
     new ServerThread(clientSocket).start();
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
