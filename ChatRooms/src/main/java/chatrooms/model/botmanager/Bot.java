package chatrooms.model.botmanager;

import chatrooms.controller.server.ClientType;
import chatrooms.model.Feed;
import chatrooms.model.chatroom.ChatRoom;
import chatrooms.model.chatroom.ThreadedConnectionChatRoom;
import chatrooms.model.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

public abstract class Bot implements Runnable {

    protected static final Random random = new Random();

    protected final Feed<String> messageFeed;
    protected final String name;
    private final boolean isLocalBot;
    private boolean kill;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * Constructor
     * @param name of bot
     * @param isLocalBot if true bot is local, if false bot is migratory
     */
    public Bot (String name, boolean isLocalBot) {
        messageFeed = new Feed<>();
        this.name = name;
        this.isLocalBot = isLocalBot;
        kill = false;
    }

    /**
     * @return next string bot will send
     */
    protected abstract String nextString();

    /**
     * @return greeting of the bot
     */
    protected abstract String greeting();

    /**
     * run method of bot
     */
    public void run() {
        while (!kill) {
            Socket socket = establishConnection();
            if (socket == null) return;
            beInChatRoom(socket);
        }
    }

    /**
     * connects bot to the chatroom
     * @return socket connected to the chatroom
     */
    private Socket establishConnection() {
        int port = askForPortNumber();
        if (port == -1) return null;
        Socket socketChatRoom = new Socket();
        try {
            socketChatRoom.connect(new InetSocketAddress(Server.IP, port), 1000);
            in = new BufferedReader(new InputStreamReader(socketChatRoom.getInputStream()));
            out = new PrintWriter(socketChatRoom.getOutputStream(), true);
            if (!socketChatRoom.isConnected()) throw new IOException();
            return socketChatRoom;
        } catch (IOException e) {
            System.out.println("Unable to connect to chatRoom.");
        }
        return null;
    }

    /**
     * method to ask main server for the port number
     * @return port number, -1 if there is no port number available or the asking was unsuccessful
     */
    private int askForPortNumber() {
        Socket socketMS = new Socket();
        int port = -1;
        try {
            socketMS.connect(new InetSocketAddress(Server.IP, Server.PORT_NUMBER), 1000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socketMS.getInputStream()));
            PrintWriter out = new PrintWriter(socketMS.getOutputStream(), true);
            if (!socketMS.isConnected()) throw new IOException();
            out.println(ClientType.BOT + ";" + name);
            port = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            System.out.println("Unable to make connection to main server.");
        } catch (NumberFormatException nfe) {
            System.out.println("Server sent incorrectly formatted data: " + port);
        }
        closeSocket(socketMS);
        return port;
    }

    /**
     * method of behaviour of bot in chatroom, sends bot's identification and greeting to the chatroom
     * @param socket that is connected to the chatroom
     */
    private void beInChatRoom(Socket socket) {
        try {
            out.println(name);
            send(greeting());
            try {
                chatRoomLoop();
            } catch (InterruptedException e) {
                out.println("Good bye world!");
            }
            out.println(ThreadedConnectionChatRoom.DISCONNECT_SIGNAL);
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Chatroom connection problem.");
        }
        closeSocket(socket);
    }

    /**
     * loop of bot while in chatroom, sends and receives messages to and from the chatroom
     * @throws InterruptedException if bot gets interrupted
     * @throws IOException if there is connection error
     */
    private void chatRoomLoop() throws InterruptedException, IOException {
        String line = "";
        do {
            Thread.sleep(random.nextInt(BotManager.MAX_INTERVAL -
                    BotManager.MIN_INTERVAL) + BotManager.MIN_INTERVAL);
            while(in.ready() && !line.equals(ChatRoom.END_SIGNAL)) {
                line = in.readLine();
                messageFeed.add(line);
            }
            send(nextString());
        } while (!kill && !line.equals(ChatRoom.END_SIGNAL) &&
                (isLocalBot || random.nextFloat() > BotManager.CHANCE_TO_LEAVE));
    }

    /**
     * closes connection of socket if it's still connected
     * @param socket to be closed
     */
    private void closeSocket(Socket socket) {
        try {
            if (socket.isConnected()) socket.close();
        } catch (IOException e) {
            System.out.println("Unable to close the socket.");
        }
    }

    /**
     * sends bot's message and adds it to bot's message feed
     * @param s string to be send
     */
    private void send(String s) {
        out.println(s);
        messageFeed.add(s);
    }

    /**
     * setter for kill
     * @param kill value of kill to be set
     */
    public void setKill(boolean kill) {
        this.kill = kill;
    }

    /**
     * getter for message feed
     * @return message feed
     */
    public Feed<String> getMessageFeed() {
        return messageFeed;
    }

    /**
     * Getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for isLocalBot
     * @return true if is local bot
     */
    public boolean isLocalBot() {
        return isLocalBot;
    }

    /**
     * getter for isKill
     * @return true if isKill
     */
    public boolean isKill() {
        return kill;
    }
}
