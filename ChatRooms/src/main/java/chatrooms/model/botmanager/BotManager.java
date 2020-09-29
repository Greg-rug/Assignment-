package chatrooms.model.botmanager;

import chatrooms.view.BotManagerPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BotManager {

    //in seconds
    public static final int MAX_INTERVAL = 7;
    public static final int MIN_INTERVAL = 2;

    private final int numberOfBots;
    private final ArrayList<Bot> bots;
    private final ExecutorService executorService;
    private final BotFactory botFactory;

    public BotManager(int numberOfBots) {
        this.numberOfBots = numberOfBots;
        executorService = Executors.newFixedThreadPool(numberOfBots);
        bots = new ArrayList<>();
        botFactory = new BotFactory();
    }

    public void killAllBots() {
        for (Bot b: bots) b.setKill(true);
        executorService.shutdownNow();
    }

    public void spawnBots() {
        for (int i = 1; i <= numberOfBots; i++) {
            Bot newBot = generateBot();
            bots.add(newBot);
            executorService.submit(new Thread(newBot));
        }
    }

    private Bot generateBot() {
        return botFactory.getRandomBot();
    }
}
