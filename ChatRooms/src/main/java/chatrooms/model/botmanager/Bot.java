package chatrooms.model.botmanager;

import chatrooms.model.Feed;
import chatrooms.model.ConnectionHandler;
import chatrooms.model.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

public abstract class Bot implements Runnable {

    private static final double CHANCE_TO_LEAVE = 0.3;

    protected final Feed<String> messageFeed;
    protected final String name;
    private final boolean isLocalBot;
    private boolean kill;
    private BufferedReader in;
    private PrintWriter out;

    public Bot (String name, boolean isLocalBot) {
        messageFeed = new Feed<>();
        this.name = name;
        this.isLocalBot = isLocalBot;
        kill = false;
    }

    public abstract String nextString();

    private int askForPortNumber() {
        Socket socketMS = new Socket();
        int port = -1;
        try {
            socketMS.connect(new InetSocketAddress("localhost", Server.PORT_NUMBER), 1000);
            if (!socketMS.isConnected()) throw new IOException();
            PrintWriter out = new PrintWriter(socketMS.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socketMS.getInputStream()));
            out.println("BOT;" + name + ";" + 0);
            port = Integer.parseInt(in.readLine());
            socketMS.close();
        } catch (IOException e) {
            System.out.println("Unable to make connection to main server.");
            return -1;
        } catch (NumberFormatException nfe) {
            System.out.println("Server sent incorrectly formatted data: " + port);
            return -1;
        }
        return port;
    }

    private void beInChatRoom(Socket socket) {
        Random random = new Random();
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(name);
            send("Hello, my name is " + name);
            try {
                while (!kill) {
                    while(in.ready()) {
                        messageFeed.add(in.readLine());
                    }
                    send(nextString());
                    if (kill || (!isLocalBot && random.nextFloat() < CHANCE_TO_LEAVE)) throw new InterruptedException();
                    Thread.sleep(1000 * (random.nextInt(BotManager.MAX_INTERVAL -
                            BotManager.MIN_INTERVAL + 1) + BotManager.MIN_INTERVAL));
                }
            } catch (InterruptedException e) {
                out.println(ConnectionHandler.DISCONNECT_SIGNAL);
            }
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Chatroom connection problem.");
        }
    }

    private Socket establishConnection() {
        int port = askForPortNumber();;
        if (port == -1) return null;
        Socket socketChatRoom = new Socket();
        try {
            socketChatRoom.connect(new InetSocketAddress("localhost", port), 1000);
            in = new BufferedReader(new InputStreamReader(socketChatRoom.getInputStream()));
            if (!socketChatRoom.isConnected()) throw new IOException();
            return socketChatRoom;
        } catch (IOException e) {
            System.out.println("Unable to connect to chatRoom.");
        }
        return null;
    }

    public void run() {
        while (!kill) {
            Socket socket = establishConnection();
            if (socket == null) return;
            beInChatRoom(socket);
        }
    }

    public void setKill(boolean kill) {
        this.kill = kill;
    }

    private void send(String s) {
        out.println(s);
        messageFeed.add(s);
    }

}
