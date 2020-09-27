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
import java.util.Random;

public abstract class Bot implements PropertyChangeListener, Runnable {

    protected final MessageFeed messageFeed;
    protected final String name;
    private boolean isLocalBot;
    private boolean changeRoom;
    private static final int portNumberMS = 6543;

    public Bot (String name, boolean isLocalBot) {
        messageFeed = new MessageFeed();
        this.name = name;
        this.isLocalBot = isLocalBot;
        changeRoom = false;
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

    private void beInChatRoom(Socket socket) {
        Random random = new Random();
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(name);
            out.println("Hello, my name is " + name);

            while (!changeRoom) {
                Thread.sleep(1000 * (random.nextInt(6) + 2));
                out.println(nextString());
            }
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Unable to make connection to main server.");
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void run() {
        int port = askForPortNumber();
        Socket socketChatRoom = new Socket();
        try {
            socketChatRoom.connect(new InetSocketAddress("localhost", port), 1000);
            if (!socketChatRoom.isConnected()) throw new IOException();
            beInChatRoom(socketChatRoom);
        } catch (IOException e) {
            System.out.println("Unable to connect to chatRoom.");
        }
    }
}
