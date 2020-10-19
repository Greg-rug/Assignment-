package aoop.asteroids.model.online;

import aoop.asteroids.util.Pair;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ThreadedConnectionHandler extends PacketHandler {

    private DatagramSocket ds;
    private Pair<InetAddress,Integer> connection;

    public ThreadedConnectionHandler(Pair<InetAddress, Integer> connection) {
        super();
        this.connection = connection;
        try {
            ds = new DatagramSocket();
        } catch (IOException e) {
            System.out.println("Connection problem");
        }

    }

    @Override
    public void run() {
        running = true;
        while (running) {
            send(ds, 1, connection.getFirst(), connection.getSecond());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
        }
    }
}
