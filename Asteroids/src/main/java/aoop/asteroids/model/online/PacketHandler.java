package aoop.asteroids.model.online;

import aoop.asteroids.game_observer.GameUpdateListener;
import aoop.asteroids.model.game.Game;
import aoop.asteroids.util.ByteUtil;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class PacketHandler {

    public static final int MAX_SIZE = 2048;

    private static final int MAX_CONNECTIONS = 20;

    protected final ExecutorService executorService;

    protected boolean running;

    public PacketHandler() {
        running = false;
        executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);
    }

    public void send(DatagramSocket ds, int signal, Game game, InetAddress ip, int port) {
        ByteUtil bytes = new ByteUtil();
        bytes.add(signal);
        bytes.add(game);
        byte[] data = bytes.getByteArray();
        try {
            System.out.println("Sent length = " + data.length);
            ds.send(new DatagramPacket(data, data.length, ip, port));
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

    public void send(DatagramSocket ds, int signal, int value, InetAddress ip, int port) {
        ByteUtil bytes = new ByteUtil();
        bytes.add(signal);
        bytes.add(value);
        byte[] data = bytes.getByteArray();
        try {
            ds.send(new DatagramPacket(data, data.length, ip, port));
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

    public DatagramPacket receive(DatagramSocket ds) {
        byte[] data = new byte[MAX_SIZE];
        DatagramPacket receivePacket = new DatagramPacket(data, data.length);
        try {
            ds.receive(receivePacket);
            return receivePacket;
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
        return null;
    }

    public byte[] receiveGame(DatagramSocket ds) {
        DatagramPacket dp = receive(ds);
        assert dp != null;
        return dp.getData();
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }
}
