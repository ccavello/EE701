/******************************************************************
                Christopher D. Cavello
                June 12, 1997
                ee 701
                project 3
*******************************************************************/


/*
		cdcClient

	This program is a modified version of "QuoteClient" found
	on JavaSoft's page about Datagrams.
*/

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

import java.net.*;
import java.io.*;
import java.util.*;



public class cdcClient  extends Thread
{
	public static void main(String[] args) 
	{
		int port;
		int T=(int)(Math.random()*2000)+1000;     
		InetAddress address;
		DatagramSocket socket = null;
		DatagramPacket packet;
		byte[] sendBuf = new byte[256];
		if (args.length != 2) 
		{
			System.out.println("Usage: java cdcClient <hostname> <port#>");
			return;
		}
		while( 1 !=  0)						//  1 is never equal to 0
		{   
			T=(int)(Math.random()*2000)+1000;     
			try 
			{
				Thread.sleep(T);
			} 
			catch (InterruptedException e)
			{
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
 					port = Integer.parseInt(args[1]);
					address = InetAddress.getByName(args[0]);
					packet = new DatagramPacket(sendBuf, 256, address, port);
					socket.send(packet);
  	  		// get response
					packet = new DatagramPacket(sendBuf, 256);
					socket.receive(packet);
 					String received = new String(packet.getData(), 0);
					System.out.println("current time: " + received);
					socket.close();
				} 
				catch (IOException e) 
				{
					System.err.println("IOException:  " + e);
					e.printStackTrace();
				}
			}
 		} // end while 1!=0
	}  /* end of public static void main(String[] args) */	
}    /* end of public class cdcClient   */
