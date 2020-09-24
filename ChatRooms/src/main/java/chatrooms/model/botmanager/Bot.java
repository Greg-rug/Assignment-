package chatrooms.model.botmanager;

import chatrooms.model.MessageFeed;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public abstract class Bot implements PropertyChangeListener {

    private MessageFeed messageFeed;
    private String name;
    private boolean isLocalBot;
    private boolean connectionActive;

    public Bot (MessageFeed messageFeed, String name, boolean isLocalBot) {
        messageFeed.addListener(this);
        this.messageFeed = messageFeed;
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
