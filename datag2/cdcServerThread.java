/******************************************************************
                Christopher D. Cavello
                June 12, 1997
                ee 701
                project 3
*******************************************************************/

/*
		cdcServerThread

	This program is a modified version of "QuoteServerThread" found
	on JavaSoft's page about Datagrams.
*/

import java.io.*;
import java.net.*;
import java.util.*;

class cdcServerThread extends Thread 
{
	private DatagramSocket socket = null;
	private DataInputStream qfs = null;
	cdcServerThread() 
	{
		super("cdcServer");
		try 
		{
			socket = new DatagramSocket();
			System.out.println("cdcServer listening on port: " + socket.getLocalPort());
		} 
		catch (java.io.IOException e) 
		{
			System.err.println("Could not create datagram socket.");
		}

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
				packet = new DatagramPacket(buf, 256);	// receive request
				socket.receive(packet);
				address = packet.getAddress();
				port = packet.getPort();
				dString = new Date().toString();				// send response
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
}
