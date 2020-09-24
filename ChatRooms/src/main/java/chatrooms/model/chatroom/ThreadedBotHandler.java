package chatrooms.model.chatroom;

import chatrooms.model.MessageFeed;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadedBotHandler implements Runnable, PropertyChangeListener {

    private final Socket socket;
    private final MessageFeed messageFeed;

    public ThreadedBotHandler(Socket socket, MessageFeed messageFeed) {
        messageFeed.addListener(this);
        this.socket = socket;
        this.messageFeed = messageFeed;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String line;
            while ((line = in.readLine()) != null) {
                messageFeed.setMessage(line);
            }

        } catch (IOException e) {
            System.out.println("TCP error");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Cannot close socket");
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println(messageFeed.getMessage());
        } catch (IOException e) {
            System.out.println("Cannot send to BOT, no connection");
        }
    }

}
