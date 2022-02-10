/******************************************************************
                Christopher D. Cavello
                June 12, 1997
                ee 701
                project 3
*******************************************************************/

/*
 * Copyright (c) 1995, 1996 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL purposes and without
 * fee is hereby granted provided that this copyright notice
 * appears in all copies. Please refer to the file "copyright.html"
 * for further important copyright and licensing information.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */
import java.io.*;
import java.net.*;
import java.util.*;

class QuoteClient 
{
	public static void main(String[] args) 
	{
		int port;
		InetAddress address;
		DatagramSocket socket = null;
		DatagramPacket packet;
		byte[] sendBuf = new byte[256];
		if (args.length != 2) 
		{
			System.out.println("Usage: java QuoteClient <hostname> <port#>");
			return;
		}
		try 
		{
		// bind to the socket
		socket = new DatagramSocket();
		} 
		catch (java.io.IOException e) 
		{
			System.err.println("Could not create datagram socket.");
		}
		if (socket != null) 
		{
			try 
			{
 				// send request
 				port = Integer.parseInt(args[1]);
				address = InetAddress.getByName(args[0]);
				packet = new DatagramPacket(sendBuf, 256, address, port);
				socket.send(packet);
  	  	// get response
				packet = new DatagramPacket(sendBuf, 256);
				socket.receive(packet);
 				String received = new String(packet.getData(), 0);
				System.out.println("Quote of the Moment: " + received);
				socket.close();
			} 
			catch (IOException e) 
			{
				System.err.println("IOException:  " + e);
				e.printStackTrace();
			}
		}
	}	
}
