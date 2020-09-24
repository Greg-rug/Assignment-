package chatrooms;

import chatrooms.model.botmanager.BotManager;

public class BotManagerMain {

    public static void main(String[] args) {
        BotManager botManager = new BotManager(15);
        botManager.start();
    }
}
