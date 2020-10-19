package aoop.asteroids.model.online;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class PacketHandler implements Runnable {

    private static final int MAX_CONNECTIONS = 20;
    private static final int MAX_SIZE = 120;
    protected ExecutorService executorService;
    protected boolean running;

    public PacketHandler() {
        running = false;
        executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);
    }

    public byte[] getByteData(int value) {
        ByteBuffer b = ByteBuffer.allocate(4);
        b.putInt(value);
        return b.array();
    }

    public void send(DatagramSocket ds, int value, InetAddress ip, int port) {
        byte[] data = getByteData(value);

        try {
            ds.send(new DatagramPacket(data, data.length, ip, port));
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

    public DatagramPacket receive(DatagramSocket ds) {
        byte[] data = new byte[MAX_SIZE];
        DatagramPacket packet = new DatagramPacket(data, data.length);

        try {
            ds.receive(packet);
            return packet;
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
        return null;
    }

    public int receiveInt(DatagramSocket ds) {
        DatagramPacket dp = receive(ds);
        assert dp != null;
        ByteBuffer wrapped = ByteBuffer.wrap(dp.getData());
        return wrapped.getInt();
    }
}
