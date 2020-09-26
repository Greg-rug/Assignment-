package chatrooms.model.botmanager;

import chatrooms.model.MessageFeed;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public abstract class Bot implements PropertyChangeListener, Runnable {

    private final MessageFeed messageFeed;
    private final String name;
    private boolean isLocalBot;
    private boolean changeRoom;
    private static final int portNumberMS = 6543;
    private int portChatRoom;

    public Bot (String name, boolean isLocalBot) {
        messageFeed = new MessageFeed();
        this.name = name;
        this.isLocalBot = isLocalBot;
        changeRoom = false;
        portChatRoom = -1;
    }

    public abstract String nextString();

    private int askForPortNumber() {
        Socket socketMS = new Socket();
        int port = -1;
        try {
            socketMS.connect(new InetSocketAddress("localhost", portNumberMS), 1000);
            if (!socketMS.isConnected()) throw new IOException();
        } catch (IOException e) {
            System.out.println("Unable to make connection to main server.");
            return -1;
        }
        try (PrintWriter out = new PrintWriter(socketMS.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socketMS.getInputStream()))) {

            out.println("BOT;" + name + ";" + 0);
            port = Integer.parseInt(in.readLine());
            in.close();
            out.close();
            socketMS.close();

        } catch (IOException e) {
            System.out.println("Unable to make connection to main server.");
            return -1;
        } catch (NumberFormatException nfe) {
            System.out.println("Server sent incorrectly formated data");
            return -1;
        }
        return port;
    }

    private void beInChatRoom() {
        while (!changeRoom) {
            /*
            try {

            } catch (IOException e) {
                System.out.println("Connection problem");
            }

             */
        }
    }


    public void run() {
        int port = askForPortNumber();
        Socket socketChatRoom = new Socket();
        try {
            socketChatRoom.connect(new InetSocketAddress("localhost", portChatRoom), 1000);
            if (!socketChatRoom.isConnected()) throw new IOException();
            beInChatRoom();
        } catch (IOException e) {
            System.out.println("Unable to connect to chatRoom.");
        }
    }
}
