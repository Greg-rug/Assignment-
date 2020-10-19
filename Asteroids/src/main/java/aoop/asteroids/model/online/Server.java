package aoop.asteroids.model.online;

import aoop.asteroids.util.Pair;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server extends PacketHandler{

    public static final int PORT_NUMBER = 55554;

    public static final String IP_ADDRESS = "localhost";

    public Server() {
        super();
    }

    public Pair<InetAddress,Integer> connect(DatagramSocket ds) {
        DatagramPacket initial = receive(ds);
        assert initial != null;
        return new Pair<>(initial.getAddress(), initial.getPort());
    }

    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(PORT_NUMBER)) {
            running = true;
            while (running) {
                Pair<InetAddress,Integer> connectionPair = connect(socket);
                executorService.submit(new ThreadedConnectionHandler(connectionPair));
            }
        } catch (IOException e) {
            System.out.println("Connection problem");
        }
    }
}
