
import java.awt.*;
import java.io.*;
import java.util.*;
import java.net.*;
import FuncInfo;
import linkList;

public class Script
{
   linkList list;               // the linked list for the script
   linkList ptr,start;          // the current line and start of the list
   int ok;
   String scrpt;
   URL documentURL;

   ///////////////////////////////////////////////////////////////////
   // The constructor
   public Script(URL url, String s)
   {
      scrpt = s;
      documentURL = url;
      if(initScript() == -1)
      {
         ok = -1;
      }
      else
      {
         ok = 1;
      }
   }

   //////////////////////////////////////////////////////////////////
   // create the final text line from parsing the store line
   //   Add any codes (ie \t, \r, \g, \b, etc.) here to parse
   //   out of the text line.
   FuncInfo parseLine(FuncInfo fi)
   {
      String tmp;
      String time;
      String month[] = {"Jan","Feb","Mar","Apr","May","Jun",
                        "Jul","Aug","Sept","Oct","Nov","Dec"};
      String Month[] = {"January","February","March","April","May","June",
                        "July","August","September","October","November","December"};
      String day[] = {"Sun","Mon","Tues","Wed","Thur","Fri","Sat"};
      String Day[] = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
      String ddmmyy;
      int min;
      int pm;
      Date date = new Date();
      int a,b;
      int i;
      char c;
      String t;         // The tag  (eg. text=Hello \ythere,  t=y)

      tmp = fi.store;
      fi.color = "";

      if(fi.func == 0 || (fi.func >= 2 && fi.func <= 97))
      {
         c = 'r';  // the default color
         b = 0;
         while(b < tmp.length())
         {
            if(tmp.charAt(b) == '\\')  // if there is a '\' does the following
            {                                          // letter indicate a color.
               b++;
               // Get the tag!
               if(tmp.charAt(b) == '{')
               {
                  t = tmp.substring(b+1);

                  // cut out the \{XX}
                  tmp = tmp.substring(0,b-1).concat(t.substring(t.indexOf('}')+1));
                  t = t.substring(0,t.indexOf('}'));
                  b -= 1;
               }
               else
               {
                  t = tmp.substring(b,b+1);
                  tmp = (tmp.substring(0,b-1)).concat(tmp.substring(b+1));  // take the "\r" out
                  b -= 1;
               }

               // set the 
               if(t.length() == 1 && isColor(t.charAt(0)))
               {
                  c = t.charAt(0);
               }
               else if(t.compareTo("tt") == 0)
               {
                  // it is the "time" variable!!
                  if(date.getHours() >= 12)
                     pm = 1;
                  else 
                     pm = 0;

                  if(pm == 1)
                  {
                     a = date.getHours();
                     if(a == 12)
                        time  = String.valueOf(12);
                     else
                        time = String.valueOf(date.getHours()-12);
                  }
                  else
                  {
                     a = date.getHours();
                     if(a == 0)
                        time = String.valueOf(12);
                     else
                        time = String.valueOf(a);
                  }

                  time = time.concat(":");
                  
                  min = date.getMinutes();
                  if(min >= 10)
                     time = time.concat(String.valueOf(min));
                  else
                  {
                     time = time.concat("0");
                     time = time.concat(String.valueOf(min));
                  }

                  if(pm == 1)
                     time = time.concat(" pm");
                     else
                     time = time.concat(" am");

                  tmp = ((tmp.substring(0,b)).concat(time)).concat(tmp.substring(b));

                  b += time.length();

                  for(i = 0; i < time.length(); i++)
                     fi.color = (fi.color).concat((new Character(c)).toString());

               } // End time
               else if(t.compareTo("dd") == 0 || t.compareTo("DD") == 0)   // Set the current date
               {
                  if(t.compareTo("dd") == 0)
                     ddmmyy = day[date.getDay()];
                  else
                     ddmmyy = Day[date.getDay()];
                  
                  // Set up the color
                  for(i = 0; i < ddmmyy.length(); i++)
                     fi.color = (fi.color).concat((new Character(c)).toString());

                  tmp = ((tmp.substring(0,b)).concat(ddmmyy)).concat(tmp.substring(b));
                  b += ddmmyy.length();
               }
               else if(t.compareTo("dn") == 0)
               {
                  ddmmyy = String.valueOf(date.getDate());

                  // Set up the color
                  for(i = 0; i < ddmmyy.length(); i++)
                     fi.color = (fi.color).concat((new Character(c)).toString());

                  tmp = ((tmp.substring(0,b)).concat(ddmmyy)).concat(tmp.substring(b));
                  b += ddmmyy.length();
               }
               else if(t.compareTo("mm") == 0 || t.compareTo("MM") == 0)  
               {
                  if(t.compareTo("mm") == 0)
                     ddmmyy = month[date.getMonth()];
                  else
                     ddmmyy = Month[date.getMonth()];

                  // Set up the color
                  for(i = 0; i < ddmmyy.length(); i++)
                     fi.color = (fi.color).concat((new Character(c)).toString());

                  tmp = ((tmp.substring(0,b)).concat(ddmmyy)).concat(tmp.substring(b));
                  b += ddmmyy.length();
               }
               else if(t.compareTo("mn") == 0)
               {
                  ddmmyy = String.valueOf(date.getMonth()+1);

                  // Set up the color
                  for(i = 0; i < ddmmyy.length(); i++)
                     fi.color = (fi.color).concat((new Character(c)).toString());

                  tmp = ((tmp.substring(0,b)).concat(ddmmyy)).concat(tmp.substring(b));
                  b += ddmmyy.length();
               }
               else if(t.compareTo("yy") == 0 || t.compareTo("YY") == 0)
               {
                  if(t.compareTo("YY") == 0)
                     ddmmyy = String.valueOf(date.getYear()+1900);
                  else
                     ddmmyy = String.valueOf(date.getYear()%100);

                  // Set up the color 
                  for(i = 0; i < ddmmyy.length(); i++)
                     fi.color = (fi.color).concat((new Character(c)).toString());

                  tmp = ((tmp.substring(0,b)).concat(ddmmyy)).concat(tmp.substring(b));
                  b += ddmmyy.length();

               }  // End short date
               else if(t.compareTo("\\") == 0)  // Are they trying to delimit the backslash?
               {
                  tmp = (tmp.substring(0,b)).concat(tmp.substring(b+1));  // delimit the '\'
                  b--;
               }
               else
               {
                  // A little error output
                  System.out.println("Backslash (\\) error in text line: "+ fi.store);
               }
               
            }  // END - if(tmp.charAt(b) == '\\') 
            else
            {
               b++;
               fi.color = fi.color.concat((new Character(c)).toString());
            }
            
         }  // END - for(...) 

      } // END - if(fi.func == ...)

      fi.text = tmp;
      
      return fi;
      
   }

   //////////////////////////////////////////////////////////////////
   // Read in the script into a linked list of FuncInfo's 
   int initScript()
   {
      InputStream file;
      DataInputStream dis;
      URL url;
      String line;
      int listlen;
      int dos;
      int a;

      try
      {
         url = new URL(documentURL,scrpt);
            
         file = url.openStream();
         dis = new DataInputStream(file);
      }
      catch(IOException e)
      {
         e.printStackTrace();
         return -1;
      }

      try
      {
         list = new linkList();                                    // The linked list
         start = list;                                             // The head of the list
         ptr = list;                                               // The current element
         listlen = 0;
         dos = 0;                                                  // Used to know how many Do's there are
         while((line = dis.readLine()) != null)
         {
            line = line.trim();                                    // cut off white space at the beginning and end
            if(!(line.startsWith("!!")) && (line.length() != 0))   // Not a comment or blank line
            {
               listlen++;
               ptr.fi = getFunc(line);                             // Get the function number
               if(ptr.fi.func == 97)
                  dos++;                                           // Chalk up another "Do"
               ptr.next = new linkList();
               ptr = ptr.next;  // advance to the next command
            }
         }

         // Ok now lets set the return pointers for the loops
         ptr = start;
         linkList stack[] = new linkList[dos];  // Allocate the array
         dos = 0;
         for(a=0;a<listlen;a++)
         {
            if(ptr.fi.func == 97) // A "Do"
            {
               stack[dos] = new linkList();
               stack[dos] = ptr;
               dos++;
            }
            else if(ptr.fi.func == 98)  // A Repeat
            {
               if(dos > 0)
               {
                  dos--;
                  ptr.fi.ret = stack[dos];
               }
               else
               {
                  // OMYGOSH!! Script error output!!!!
                  System.out.println("Repeat error in line : Repeat times="+ptr.fi.times);
                  System.out.println("     Mismatched Do/Repeats?");
               }
            }
            ptr = ptr.next;
         }

         ptr = start;

         file.close();
         dis.close();
      }
      catch (IOException e)
      {
         // Error!
         return -1;  // We could not read from the script.  This is a bad script path.
      }
      
      return 1;
   }  // End initScript()
}  // End Class Script
