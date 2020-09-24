package chatrooms.model.chatroom;

import chatrooms.model.MessageFeed;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatRoom {

    private final MessageFeed messageFeed;
    private int portNumber;
    private String name;

    public ChatRoom(String name) {
        this.name = name;
        messageFeed = new MessageFeed();
    }

    //replace by constant?
    private int inputMainServerPort() {
        //String portString = JOptionPane.showInputDialog("Provide server port:");
        //return Integer.parseInt(portString);
        return 6543;
    }

    public boolean reportPortNumber() {
        Socket socketMS = new Socket();
        int portMS = inputMainServerPort();
        try {
            socketMS.connect(new InetSocketAddress("localhost", portMS), 1000);
            if (!socketMS.isConnected()) throw new IOException();
        } catch (IOException e) {
            System.out.println("Unable to make connection to main server.");
            return false;
        }
        try (PrintWriter out = new PrintWriter(socketMS.getOutputStream(), true)) {

            out.println("CHATROOM;" + name + ";" + portNumber);
            out.close();
            socketMS.close();

        } catch (IOException e) {
            System.out.println("Unable to make connection to main server.");
            return false;
        }
        return true;
    }

    /*
    private void setupGUI() {
        SwingUtilities.invokeLater(() -> {
            ClientPanel panel = new ClientPanel(messageFeed, this);
            JFrame frame = new JFrame();

            frame.setContentPane(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
    */

    public void start() {
        try (ServerSocket ss = new ServerSocket(0)) {
            portNumber = ss.getLocalPort();
            if (!reportPortNumber()) {
                System.out.println("Unable to make connection");
                return;
            }

            //setupGUI();

            while (true) {
                Socket socket = ss.accept();
                Thread t = new Thread(new ThreadedBotHandler(socket, messageFeed));
                t.start();
                //t = new Thread(new ThreadedBotSender(socket, messageFeed));
                //t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}
