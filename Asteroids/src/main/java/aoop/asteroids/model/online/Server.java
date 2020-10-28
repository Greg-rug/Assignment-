package aoop.asteroids.model.online;

import aoop.asteroids.game_observer.GameUpdateListener;
import aoop.asteroids.model.game.Game;
import aoop.asteroids.util.ByteUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

public class Server extends PacketHandler implements GameUpdateListener, Runnable {

    //TODO: secure read in byte-util

    public static final int PORT_NUMBER = 55554;

    private final Game game;
    private final ArrayList<Connection> connections;

    public Server(Game game) {
        super();
        this.game = game;
        game.addListener(this);
        connections = new ArrayList<>();
    }

    @Override
    public void run() {
        try (DatagramSocket ds = new DatagramSocket(PORT_NUMBER)) {
            int shipID = -1;
            running = true;
            while (running) {
                DatagramPacket dp = receive(ds);
                ByteUtil bytes = new ByteUtil(dp.getData());
                int outcome = bytes.getInt();
                if (outcome == Client.JOIN_SIGNAL) {
                    if (findConnection(dp.getAddress()) == null) {
                        shipID = game.addSpaceShip();
                        connections.add(new Connection(game, ds, dp, shipID));
                    }
                    send(ds, Client.RECEIVED_SIGNAL, shipID, dp.getAddress(), dp.getPort());
                }
                if (outcome == Client.MAINTAIN_SIGNAL) {
                    System.out.println("MAINTAINING");
                    Connection c = findConnection(dp.getAddress());
                    if (c != null) {
                        System.out.println("MAINTAIN not NULL");
                        c.setLastTick(game.getLastTick());
                        c.setRunning(true);
                    }
                }
                System.out.println("ended");
            }
        } catch (IOException e) {
            System.out.println("Connection problem");
        }
    }

    private Connection findConnection(InetAddress IP) {
        for (Connection c: connections) {
            if (c.getInetAddres().equals(IP)) return c;
        }
        return null;
    }

    @Override
    public void onGameUpdated(long timeSinceLastTick) {
        Iterator<Connection> iter = connections.iterator();
        while(iter.hasNext()) {
            System.out.println("BEING SEND");
            Connection c = iter.next();
            c.send();
            if (game.getLastTick() - c.getLastTick() > Connection.MAX_NO_RESPONSE) {
                System.out.println("REMOVING_______");
                iter.remove();
            }
        }
    }
}
