/******************************************************************
                Christopher D. Cavello
                June 12, 1997
                ee 701
                project 3
*******************************************************************/

class QuoteServer 
{
  public static void main(String[] args) 
  {
    new QuoteServerThread().start();
  }
}
