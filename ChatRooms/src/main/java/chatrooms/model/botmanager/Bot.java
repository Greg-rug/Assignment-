package chatrooms.model.botmanager;

import chatrooms.model.MessageFeed;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public abstract class Bot implements PropertyChangeListener, Runnable {

    private final MessageFeed messageFeed;
    private final String name;
    private boolean isLocalBot;
    private boolean connectionActive;

    public Bot (String name, boolean isLocalBot) {
        messageFeed = new MessageFeed();
        this.name = name;
        this.isLocalBot = isLocalBot;
        connectionActive = true;
    }

    public abstract String nextString();

    public void run() {
        while (connectionActive) {

        }
    }
}
