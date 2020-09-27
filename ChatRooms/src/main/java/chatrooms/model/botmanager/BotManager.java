package chatrooms.model.botmanager;

import chatrooms.model.botmanager.bots.NormalBot;
import chatrooms.model.botmanager.bots.RepeatBot;
import chatrooms.view.BotManagerPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BotManager {

    //in seconds
    public static final int MAX_INTERVAL = 7;
    public static final int MIN_INTERVAL = 2;

    private static final int NUMBER_OF_BOT_TYPES = 2;
    private static final Random r = new Random();

    private final int numberOfBots;
    private final ArrayList<Bot> bots;
    private final ExecutorService executorService;

    public BotManager(int numberOfBots) {
        this.numberOfBots = numberOfBots;
        executorService = Executors.newFixedThreadPool(numberOfBots);
        bots = new ArrayList<>();
    }

    public void killAllBots() {
        for (Bot b: bots) b.setKill(true);
        executorService.shutdownNow();
    }

    public void spawnBots() {
        setupGUI();
        for (int i = 1; i <= numberOfBots; i++) {
            Bot newBot = generateBot();
            bots.add(newBot);
            executorService.submit(new Thread(newBot));
        }
    }

    private Bot generateBot() {
        switch (r.nextInt(NUMBER_OF_BOT_TYPES)) {
            //NormalBot
            case 0: return new NormalBot(BotNames.getRandomName(), r.nextBoolean());
            //RepeatBot
            case 1: return new RepeatBot(BotNames.getRandomName(), r.nextBoolean());
        }
        return null;
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
