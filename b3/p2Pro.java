/*
 */
class p2Pro extends Thread {
    private p2Cub p2Cub;
    private int number;

    public p2Pro(p2Cub c, int number) {
        p2Cub = c;
        this.number = number;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            p2Cub.put(i);
            System.out.println("p2Pro #" + this.number + " put: " + i);
            try {
                sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) {
            }
        }
    }
}
