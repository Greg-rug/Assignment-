package aoop.asteroids.model.online;

import aoop.asteroids.model.game.Game;
import aoop.asteroids.util.ByteUtil;

import java.io.IOException;
import java.net.*;

public class Client extends PacketHandler implements Runnable {

    public static final int JOIN_SIGNAL = 1;
    public static final int MAINTAIN_SIGNAL = 2;
    public static final int RECEIVED_SIGNAL = 3;
    public static final int GAME_SIGNAL = 4;

    private DatagramSocket datagramSocket;
    private final Game game;
    private InetAddress IP;

    //TODO: first int is supposed to be ship ID!!!

    public Client(Game game) {
        super();
        this.game = game;
        try {
            datagramSocket = new DatagramSocket();
            IP = InetAddress.getLocalHost();
        } catch (IOException e) {
            System.out.println("Connection problem");
        }
    }

    private boolean initialiseConnection() {
        int response;
        ByteUtil bytes;
        do {
            send(datagramSocket, JOIN_SIGNAL, 0, IP, Server.PORT_NUMBER);
            bytes = new ByteUtil(receive(datagramSocket).getData());
            response = bytes.getInt();
            System.out.println(response);
            if (response < 0) return false;
        } while (response != RECEIVED_SIGNAL);
        game.getSpaceship().setID(bytes.getInt());
        return true;
    }

    @Override
    public void run() {
        if (!initialiseConnection()) {
            System.out.println("Initialisation failed");
            return;
        }
        running = true;
        while (running) {
            send(datagramSocket, MAINTAIN_SIGNAL, 0, IP, Server.PORT_NUMBER);
            game.loadGame(receiveGame(datagramSocket));
            System.out.println("NEXT BIG THING");
        }
    }
}
