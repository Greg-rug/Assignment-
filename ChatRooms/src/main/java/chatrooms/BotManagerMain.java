package chatrooms;

import chatrooms.model.botmanager.BotManager;
import chatrooms.view.SimpleFrame;

/**
 * Main for bot manager
 */
public class BotManagerMain {

    /**
     * Title of the window
     */
    private static final String TITLE = "Kill switch";

    /**
     * Number of bots
     */
    private static final int NUM_OF_BOTS = 15;

    /**
     * creates and starts the botmanager and its frame
     * @param args no arguments used
     */
    public static void main(String[] args) {
        BotManager botManager = new BotManager(NUM_OF_BOTS);
        new SimpleFrame(botManager, TITLE);
        botManager.spawnBots();
    }
}
