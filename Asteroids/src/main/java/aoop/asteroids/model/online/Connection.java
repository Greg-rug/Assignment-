package aoop.asteroids.model.online;

import aoop.asteroids.game_observer.GameUpdateListener;
import aoop.asteroids.model.game.Game;
import aoop.asteroids.util.Pair;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ThreadedConnectionHandler extends PacketHandler implements GameUpdateListener {

    private final DatagramPacket datagramPacket;
    private DatagramSocket datagramSocket;
    private final Game game;
    private final Server server;

    public ThreadedConnectionHandler(Server server, Game game, DatagramPacket datagramPacket) {
        super();
        game.addListener(this);
        this.server = server;
        this.game = game;
        this.datagramPacket = datagramPacket;
        try {
            datagramSocket = new DatagramSocket();
        } catch (IOException e) {
            System.out.println("Connection problem");
        }

    }

    @Override
    public void run() {
        running = true;
        receive(datagramSocket);
        System.out.println();
    }

    @Override
    public void onGameUpdated(long timeSinceLastTick) {
        if (running) {
            send(datagramSocket, game, datagramPacket.getAddress(), datagramPacket.getPort());
        }
    }
}
