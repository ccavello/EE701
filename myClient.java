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
import java.util.*;
import java.io.*;
import java.net.*;


public class myClient {
    public static void main(String[] args) {
        Socket kkSocket = null;
        PrintStream os = null;
        DataInputStream is = null;

        try {
            kkSocket = new Socket("cdcpc", 4444);
            os = new PrintStream(kkSocket.getOutputStream());
            is = new DataInputStream(kkSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: cdcpc");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: cdcpc");
        }

        if (kkSocket != null && os != null && is != null) {
            try {
/*                StringBuffer buf = new StringBuffer(50);
                int c;
                String fromServer;

                while ((fromServer = is.readLine()) != null) {
                    System.out.println("Server: " + fromServer);
                    if (fromServer.equals("Bye."))
                        break;
                    while ((c = System.in.read()) != '\n') {
                        buf.append((char)c);
                    }
                    System.out.println("Client: " + buf);
                    os.println(buf.toString());
                    os.flush();
                    buf.setLength(0);
                }
*/
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
                kkSocket.close();
            } catch (UnknownHostException e) {
                System.err.println("Trying to connect to unknown host: " + e);
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }
}
