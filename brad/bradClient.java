import java.util.*;
import java.net.*;
import java.io.*;

public class Client extends Thread {
  public void run() {

    Socket clientSocket = null;    
    // socket declaration
        PrintStream os = null;          
    // write to socket
        DataInputStream is = null;     
    // read from socket

        // create socket
        try
        {
      clientSocket = new Socket("alain.eng.ohio-state.edu", 4567);
      os = new PrintStream(clientSocket.getOutputStream());
      is = new DataInputStream(clientSocket.getInputStream());
    }
    catch (UnknownHostException e)
        {
      System.err.println("Don't know about host: alain.eng.ohio-state.edu");
    }
    catch (IOException e)
        {
      System.err.println("Couldn't get I/O for the connection to: alain.eng.ohio-state.edu");
    }
    if (clientSocket != null && os != null && is != null)
    {
      try
          {
	String fromServer,toServer;
	int T=(int)(Math.random()*2000)+1000;
	sleep(T);
	toServer="Time?";
	os.println(toServer);
	os.flush();
	while ((fromServer = is.readLine()) != null)
	{
	  toServer="Time?";
	  os.println(toServer);
	  os.flush();
	  fromServer=is.readLine();
	  System.out.println("The current time is: " + fromServer);
	  sleep(T);
	}
	os.close();
	is.close();
	clientSocket.close();
      }
      catch (InterruptedException e) {
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
  }
}
