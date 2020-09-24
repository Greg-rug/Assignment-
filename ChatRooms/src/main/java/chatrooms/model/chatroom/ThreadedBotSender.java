package chatrooms.model.chatroom;

import chatrooms.model.MessageFeed;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.Socket;

public class ThreadedBotSender implements Runnable, PropertyChangeListener {

    MessageFeed messageFeed;
    Socket socket;

    public ThreadedBotSender(Socket socket, MessageFeed messageFeed) {
        this.messageFeed = messageFeed;
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {

        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
