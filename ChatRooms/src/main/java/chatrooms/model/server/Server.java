package chatrooms.model.server;

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

    public Server() {
        portNumbers = new ArrayList<>();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Starting server on port: " + portNumber);

            //is server supposed to shutdown?
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
                            if (words[1].equals("0")) {
                                System.out.println("BOT " + words[2] + " is joining the room at " + roomNumber);
                            } else {
                                System.out.println("BOT " + words[2] + " is changing room to the room at " + roomNumber);
                            }
                        }
                    }
                    if (words[0].equals("CHATROOM") && words.length == 3) {
                        //implement end signal from chat room??
                        try {
                            portNumbers.add(Integer.parseInt(words[2]));
                            System.out.println(words[1] + " is listening at port " + words[2]);
                        } catch (NumberFormatException nfe) {
                            System.out.println("CHATROOM " + words[1] + " sent incorrectly formatted string, unable to connect.");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Connection error, server shuts down.");
        }
    }

    private int selectRandomPortNumber() {
        Random r = new Random();
        if (portNumbers.size() == 0) return -1;
        return portNumbers.get(r.nextInt(portNumbers.size()));
    }

}
