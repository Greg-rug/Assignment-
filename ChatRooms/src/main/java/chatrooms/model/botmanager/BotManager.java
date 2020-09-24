package chatrooms.model.botmanager;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BotManager {

    private int numberOfBots;
    private ExecutorService executorService;

    public BotManager(int numberOfBots) {
        this.numberOfBots = numberOfBots;
        Executors.newFixedThreadPool(numberOfBots);
    }

    public void start() {
        spawnBots();
    }

    private void spawnBots() {
        //Random r = new Random();
        for (int i = 0; i < numberOfBots; i++) {
            executorService.submit(new Thread(new NormalBot("Name" + (i+1), true)));
        }
    }
}
