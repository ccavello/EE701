/*
 */
class  p2ProConT
{
    public static void main(String[] args) 
    {
        p2Cub c = new p2Cub();
        p2Pro p1 = new p2Pro(c, 1);
        p2Con c1 = new p2Con(c, 1);

        p1.start();
        c1.start();
    }
}
