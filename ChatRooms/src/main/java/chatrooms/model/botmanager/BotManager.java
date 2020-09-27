package chatrooms.model.botmanager;

import chatrooms.view.BotManagerPanel;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BotManager {

    private int numberOfBots;
    private ExecutorService executorService;

    public BotManager(int numberOfBots) {
        this.numberOfBots = numberOfBots;
        executorService = Executors.newFixedThreadPool(numberOfBots);
    }

    public void killAllBots() {
        executorService.shutdownNow();
    }

    public void spawnBots() {
        setupGUI();
        //Random r = new Random();
        for (int i = 1; i <= numberOfBots; i++) {
            executorService.submit(new Thread(new NormalBot("Name" + (i), true)));
        }
    }

    private void setupGUI() {
        SwingUtilities.invokeLater(() -> {
            BotManagerPanel panel = new BotManagerPanel(this);
            JFrame frame = new JFrame();

            frame.setContentPane(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}
