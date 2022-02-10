/******************************************************************
                Christopher D. Cavello
                June 12, 1997
                ee 701
                project 3
*******************************************************************/

/*
		cdcServer

	This program is a modified version of "QuoteServer" found
	on JavaSoft's page about Datagrams.
*/
class cdcServer extends Thread
{
	public static void main(String[] args) 
  {
    new cdcServerThread().start();
  }
}
