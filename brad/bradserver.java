import java.util.*;
import java.net.*;
import java.io.*;


class server {
  public static void main(String[] args) {
    ServerSocket serverSocket = null;
    boolean listening = true;

    try
        {
      serverSocket = new ServerSocket(4567);
    }                  catch (IOException e)
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
