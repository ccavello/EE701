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

class KKMultiServerThread extends Thread {
    Socket socket = null;

    KKMultiServerThread(Socket socket) {
        super("KKMultiServerThread");
        this.socket = socket;
    }

    public void run() {
        try {
            DataInputStream is = new DataInputStream(
                                  new BufferedInputStream(socket.getInputStream()));
            PrintStream os = new PrintStream(
                              new BufferedOutputStream(socket.getOutputStream(), 1024), false);
            KKState kks = new KKState();
            String inputLine, outputLine;

            outputLine = kks.processInput(null);
            os.println(outputLine);
            os.flush();

            while ((inputLine = is.readLine()) != null) {
                outputLine = kks.processInput(inputLine);
                os.println(outputLine);
                os.flush();
                if (outputLine.equals("Bye"))
                    break;
            }
            os.close();
            is.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
