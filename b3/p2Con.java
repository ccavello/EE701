/*
 */
class p2Con extends Thread {
    private p2Cub p2Cub;
    private int number;

    public p2Con(p2Cub c, int number) {
        p2Cub = c;
        this.number = number;
    }

    public void run() {
        int value = 0;
        for (int i = 0; i < 10; i++) {
            value = p2Cub.get();
            System.out.println("p2Con #" + this.number + " got: " + value);
        }
    }
}

