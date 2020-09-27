package chatrooms.model.server;

import chatrooms.model.MessageFeed;
import chatrooms.view.TextFeedPanel;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {

    private final ArrayList<Integer> portNumbers;
    private static final int portNumber = 6543;
    private final MessageFeed messageFeed;

    public Server() {
        portNumbers = new ArrayList<>();
        messageFeed = new MessageFeed();
    }

    public void start() {
        setupGUI();
        messageFeed.setMessage("Starting server at port " + portNumber);
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            messageFeed.setMessage("Serve successfully started at port " + portNumber);
            //is server ever suppose to shutdown?
            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                String line =  in.readLine();
                if (line != null) {
                    String [] words = line.split(";");
                    if (words[0].equals("BOT") && words.length == 3) {
                        int roomNumber = selectRandomPortNumber();
                        if (roomNumber != -1) {
                            out.println(roomNumber);
                            if (words[2].equals("0")) {
                                messageFeed.setMessage("BOT " + words[1] + " is joining the room at " + roomNumber);
                            } else {
                                messageFeed.setMessage("BOT " + words[1] + " is changing the room to " + roomNumber);
                            }
                        }
                    }
                    if (words[0].equals("CHATROOM") && words.length == 3) {
                        //implement end signal from chat room??
                        try {
                            portNumbers.add(Integer.parseInt(words[2]));
                            messageFeed.setMessage(words[1] + " is listening at port " + words[2]);
                        } catch (NumberFormatException nfe) {
                            messageFeed.setMessage("CHATROOM " + words[1] + " sent incorrectly formatted string, unable to connect.");
                        }
                    }
                }
            }
        } catch (IOException e) {
            messageFeed.setMessage("Connection error, server shuts down.");
        }
    }

    private void setupGUI() {
        SwingUtilities.invokeLater(() -> {
            TextFeedPanel panel = new TextFeedPanel(messageFeed);
            JFrame frame = new JFrame();

            frame.setContentPane(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }

    private int selectRandomPortNumber() {
        Random r = new Random();
        if (portNumbers.size() == 0) return -1;
        return portNumbers.get(r.nextInt(portNumbers.size()));
    }

}
