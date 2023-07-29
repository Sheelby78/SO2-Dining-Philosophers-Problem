public class Philosopher implements Runnable {

    private final Object leftFork;
    private final Object rightFork;
    protected ShowUpdate psi;
    private String actualActivity;

    public String getActualActivity() {
        return actualActivity;
    }
    Philosopher(Object left, Object right) {
        this.leftFork = left;
        this.rightFork = right;
    }
    public void setPsi(ShowUpdate psi) {
        this.psi = psi;
    }

    @Override
    public void run() {
        try {
            while (true) {
                actualActivity = "Thinking";
                psi.showStatus();
                Thread.sleep(((int) (Math.random() * 1000)));
                synchronized (leftFork) {
                    synchronized (rightFork) {
                        actualActivity = "Eating";
                        psi.showStatus();
                        Thread.sleep((int) (Math.random() * 2000));
                    }
                    actualActivity = "Thinking";
                    psi.showStatus();
                    Thread.sleep(((int) (Math.random() * 1000)));
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}