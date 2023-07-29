import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class GUI {
    private JButton startButton;
    private JButton stopButton;
    private JPanel mainPanel;
    private JPanel buttonsPanel;
    private BufferedImage image = null;
    private JPanel actionPanel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel[] labels = {label1, label2, label3, label4, label5};
    private ArrayList<Thread> allThreads = new ArrayList<>();

    public JPanel getMainPanel() {
        return mainPanel;
    }
    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    public void start() {
        Philosopher[] philosophers = new Philosopher[5];
        Object[] forks = new Object[philosophers.length];

        Arrays.setAll(forks, i -> new Object());

        for (int i = 0; i < philosophers.length; i++) {

            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];

            if(i != philosophers.length-1) {
                philosophers[i] = new Philosopher(leftFork, rightFork);
            } else {
                philosophers[i] = new Philosopher(rightFork, leftFork);
            }
                int finalI = i;
                philosophers[i].setPsi(
                        new ShowUpdate() {
                            public void showStatus() {
                                labels[finalI].setText("P " + (finalI + 1) + " " + philosophers[finalI].getActualActivity());
                            }
                        }
                );

            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            allThreads.add(t);
            t.start();
        }
    }
    public void stop(){
        for (Thread allThread : allThreads) {
            allThread.interrupt();
        }
    }
    public GUI() {
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
    }


}
