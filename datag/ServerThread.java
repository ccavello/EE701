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
import java.lang.*; 


class ServerThread extends Thread 
{
  Socket socket = null;
  ServerThread(Socket socket) 
  {
    super("ServerThread");
    this.socket=socket;
  }
  public void run() 
	{
		try 
		{
      DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
      PrintStream out = new PrintStream(new BufferedOutputStream(socket.getOutputStream(), 1024), false);
      String inputLine,outputLine;
      int seconds, minutes, hours, year, dayofmonth;
			String ddmmyy, month, day;
			String Day[] = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
      String Month[] = {"January","February","March","April","May","June",
                        "July","August","September","October","November","December"};
			while  ((inputLine = in.readLine()) != null) 
      {
				Date now=new Date();
				hours=now.getHours();
				minutes=now.getMinutes();
				seconds=now.getSeconds();
				day=  Day[now.getDay()] ;  // convert 0-6 into a day of the week
				month=Month[now.getMonth()];  // convert 0-11 into a month of the year
				year=now.getYear();
				dayofmonth=now.getDate();
        /* some of the above lines from Javasoft's  "Clock.java" program   */
        /* some of below line (hours and minutes and AM/PM) borrowed from a friend's web page	 */
				outputLine="" + ((hours >12) ? hours -12 : hours) + ((minutes <10) ? ":0" : ":") + minutes
						+ ((seconds <10) ? ":0" : ":") + seconds + ((hours >=12)  ? " PM" : " AM") + "   "
						+ day + ", " + month+ " " +dayofmonth + ", 19"+(year) ;
				if (inputLine.equals("Time?"))  //  See the "KnockKnock" examples too
        {
					out.println(outputLine);
					out.flush();
				}
      }  /* end of while loop  */
      out.close();
      in.close();
      socket.close();
    } 
    catch (IOException e) 
    {
      e.printStackTrace();
    }

  }  /* end of "public void run()"  */
}  /* end of "class ServerThread extends Thread" */
