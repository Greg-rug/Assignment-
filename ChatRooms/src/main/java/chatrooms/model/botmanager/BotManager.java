package chatrooms.model.botmanager;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is a class for BOT Manager
 */
public class BotManager {

    /**
     * Chance that bot will leave the chatroom after sending message (on the scale of 0 to 1)
     */
    public static final double CHANCE_TO_LEAVE = 0.3;

    /**
     * Maximal interval of bot waiting before sending the next mwssage in mili seconds
     */
    public static final int MAX_INTERVAL = 10000;

    /**
     * Minimal interval of bot waiting before sending the next mwssage in mili seconds
     */
    public static final int MIN_INTERVAL = 3000;

    private final ArrayList<Bot> bots;
    private final ExecutorService executorService;
    private final BotFactory botFactory;
    private final int numOfBots;

    /**
     * Constructor
     * @param numOfBots number of bots to be deployed
     */
    public BotManager (int numOfBots) {
        this.numOfBots = numOfBots;
        executorService = Executors.newFixedThreadPool(numOfBots);
        bots = new ArrayList<>();
        botFactory = new BotFactory();
    }

    /**
     * Spawns all bots, each bot with it's own thread
     */
    public void spawnBots() {
        for (int i = 0; i < numOfBots; i++) {
            Bot newBot = botFactory.getRandomBot();
            bots.add(newBot);
            executorService.submit(new Thread(newBot));
        }
    }

    /**
     * Kills all bots
     */
    public void killAllBots() {
        for (Bot b: bots) b.setKill(true);
        executorService.shutdownNow();
    }
}
