package aoop.asteroids.model.online;

import aoop.asteroids.model.game.Game;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Connection extends PacketHandler {

    public static final int MAX_NO_RESPONSE = 30;

    private final DatagramPacket dp;
    private final DatagramSocket ds;
    private final Game game;
    private int lastTick;
    private final int shipID;

    public Connection(Game game, DatagramSocket ds, DatagramPacket dp, int shipID) {
        super();
        this.game = game;
        this.dp = dp;
        this.ds = ds;
        this.shipID = shipID;
        lastTick = game.getLastTick();
        sendShipId();
    }

    public void sendShipId() {
        send(ds, Client.RECEIVED_SIGNAL, shipID, dp.getAddress(), dp.getPort());
    }

    public void send() {
        if (running && MAX_NO_RESPONSE > game.getLastTick() - lastTick) {
            System.out.println("Sending");
            send(ds, Client.GAME_SIGNAL, game, dp.getAddress(), dp.getPort());
        }
        if (!running) {
            sendShipId();
        }
    }

    public int getLastTick() {
        return lastTick;
    }

    public void setLastTick(int lastTick) {
        this.lastTick = lastTick;
    }

    public InetAddress getInetAddres() {
        return dp.getAddress();
    }
}
