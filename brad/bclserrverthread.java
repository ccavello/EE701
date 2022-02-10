          import java.util.*;
          import java.net.*;
          import java.io.*;
          import java.lang.*;

	  class serverthread extends Thread {
		  Socket socket = null;
		
		  serverthread(Socket socket) {
			super("serverthread");
			this.socket=socket;
		  }

		  public void run() {
		     try {
			DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			PrintStream out = new PrintStream(new BufferedOutputStream(socket.getOutputStream(), 1024), false);
			String inputLine,outputLine;
			int seconds, minutes, hours;
			while  ((inputLine = in.readLine()) != null) {
			        Date now=new Date();
				hours=now.getHours();
				minutes=now.getMinutes();
				seconds=now.getSeconds();
				outputLine="" + ((hours >12) ? hours -12 : hours) + ((minutes <10) ? ":0" : ":") + minutes + ((seconds <10) ? ":0" : ":") + seconds + ((hours >=12) ? " PM" : " AM");
				if (inputLine.equals("Time?")) {
				        out.println(outputLine);
					out.flush();
				}}
			out.close();
			in.close();
			socket.close();
		  } catch (IOException e) {
		      e.printStackTrace();
		  }
		}
	  }

