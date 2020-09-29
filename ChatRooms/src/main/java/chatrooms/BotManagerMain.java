package chatrooms;

import chatrooms.model.botmanager.BotManager;
import chatrooms.view.SimpleFrame;

public class BotManagerMain {

    private static final String TITLE = "Kill switch";

    public static void main(String[] args) {
        BotManager botManager = new BotManager(5);
        new SimpleFrame(botManager, TITLE);
        botManager.spawnBots();
    }
}
