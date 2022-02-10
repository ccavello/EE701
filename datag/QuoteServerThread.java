/******************************************************************
                Christopher D. Cavello
                June 12, 1997
                ee 701
                project 3
*******************************************************************/

/*
		QuoteServerThread
 */

import java.io.*;
import java.net.*;
import java.util.*;

class QuoteServerThread extends Thread 
{
	private DatagramSocket socket = null;
	private DataInputStream qfs = null;
	QuoteServerThread() 
	{
		super("QuoteServer");
		try 
		{
			socket = new DatagramSocket();
			System.out.println("QuoteServer listening on port: " + socket.getLocalPort());
		} 
		catch (java.io.IOException e) 
		{
			System.err.println("Could not create datagram socket.");
		}
		this.openInputFile();
	}
	public void run() 
	{
		if (socket == null)
    return;
		while (true) 
		{
			try 
			{
				byte[] buf = new byte[256];
				DatagramPacket packet;
				InetAddress address;
				int port;
				String dString = null;
				// receive request
				packet = new DatagramPacket(buf, 256);
				socket.receive(packet);
				address = packet.getAddress();
				port = packet.getPort();
				// send response
				if (qfs == null)
					dString = new Date().toString();
				else
					dString = getNextQuote();
				dString.getBytes(0, dString.length(), buf, 0);
				packet = new DatagramPacket(buf, buf.length, address, port);
				socket.send(packet);
			} 
			catch (IOException e) 
			{
				System.err.println("IOException:  " + e);
				e.printStackTrace();
			}
		}
	}
	protected void finalize() 
	{
		if (socket != null) 
		{
			socket.close();
			socket = null;
			System.out.println("Closing datagram socket.");
		}
	}
	private void openInputFile() 
	{
		try 
		{
			qfs = new DataInputStream(new FileInputStream("one-liners.txt"));
		} 
		catch (java.io.FileNotFoundException e) 
		{
			System.err.println("Could not open quote file. Serving time instead.");
		}
	}
	private String getNextQuote() 
	{
		String returnValue = null;
		try 
		{
			if ((returnValue = qfs.readLine()) == null) 
			{
				qfs.close();
				this.openInputFile();
				returnValue = qfs.readLine();    // we know the file has at least one input line!
			}
		} 
		catch (IOException e) 
		{
			returnValue = "IOException occurred in server.";
		}
		return returnValue;
	}

}
