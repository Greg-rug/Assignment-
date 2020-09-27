package chatrooms.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadedConnectionHandler implements Runnable, PropertyChangeListener {

    public static final String DISCONNECT_SIGNAL = "REQEFAOINSFOIASNFOAIPASDNASDNOINCPAE";

    private final Socket socket;
    private final MessageFeed messageFeed;
    private final PrintWriter out;
    private int chatRoomPortNumber;
    private ArrayListFeed portNumbers;
    private boolean isChatRoom;

    public ThreadedConnectionHandler(Socket socket, MessageFeed messageFeed) throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        messageFeed.addListener(this);
        chatRoomPortNumber = -2;
        this.socket = socket;
        this.messageFeed = messageFeed;
        isChatRoom = true;
    }

    public ThreadedConnectionHandler(Socket socket, MessageFeed messageFeed,
                                     ArrayListFeed portNumbers) throws IOException {
        this(socket, messageFeed);
        portNumbers.addListener(this);
        chatRoomPortNumber = -1;
        this.portNumbers = portNumbers;
        isChatRoom = false;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String line = in.readLine();
            if (line != null) {
                if (chatRoomPortNumber != -2) {
                    serverRun(line);
                    /*
                    while (socket.isConnected()) {
                        int i = 9;
                    }
                    portNumbers.delete(chatRoomPortNumber);
                    messageFeed.setMessage("Chatroom at " + chatRoomPortNumber + " is no longer available");

                     */
                    return;
                }
                String botName = line;
                messageFeed.setMessage("BOT " + botName + " has joined the chatroom");
                while ((line = in.readLine()) != null && !line.equals(DISCONNECT_SIGNAL)) {
                    messageFeed.setMessage("BOT " + botName + ": " +line);
                }
                messageFeed.setMessage("BOT " + botName + " has disconnected");
            }
        } catch (IOException e) {
            messageFeed.setMessage("TCP Error");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                messageFeed.setMessage("Cannot close socket");
            }
        }
    }

    private void serverRun(String line) {
        String [] words = line.split(";");
        if (words[0].equals("BOT") && words.length == 3) {
            chatRoomPortNumber = portNumbers.getRandom();
            if (chatRoomPortNumber > 0) {
                out.println(chatRoomPortNumber);
                if (words[2].equals("0")) {
                    messageFeed.setMessage("BOT " + words[1] + " is joining the room at " + chatRoomPortNumber);
                } else {
                    messageFeed.setMessage("BOT " + words[1] + " is changing the room to " + chatRoomPortNumber);
                }
            }
        }
        if (words[0].equals("CHATROOM") && words.length == 3) {
            try {
                chatRoomPortNumber = Integer.parseInt(words[2]);
                portNumbers.add(chatRoomPortNumber);
                messageFeed.setMessage(words[1] + " is listening at port " + words[2]);
            } catch (NumberFormatException nfe) {
                messageFeed.setMessage("CHATROOM " + words[1] + " sent incorrectly formatted string, unable to connect.");
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (isChatRoom) out.println(messageFeed.getMessage());
    }

}
