package chatrooms.model.chatroom;

import chatrooms.model.MessageFeed;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadedConnectionHandler implements Runnable, PropertyChangeListener {

    private final Socket socket;
    private final MessageFeed messageFeed;

    public ThreadedConnectionHandler(Socket socket, MessageFeed messageFeed) {
        messageFeed.addListener(this);
        this.socket = socket;
        this.messageFeed = messageFeed;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String botName;
            String line = in.readLine();
            if (line == null) {
                messageFeed.setMessage("BOT failed to identify themself, ending connection");
                in.close();
                socket.close();
                return;
            }
            else {
                botName = line;
                messageFeed.setMessage("BOT " + botName + " joins the chatroom");
            }
            while ((line = in.readLine()) != null) {
                messageFeed.setMessage("BOT " + botName + ": " +line);
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
