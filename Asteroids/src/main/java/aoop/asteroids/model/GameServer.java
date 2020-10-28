package aoop.asteroids.model;

import aoop.asteroids.model.game.Game;
import aoop.asteroids.model.online.Client;
import aoop.asteroids.model.online.Server;

public class GameServer {

    private boolean highScores;

    private final Game game;

    private Thread server;

    private Thread client;

    public GameServer() {
        game = new Game();
        server = new Thread(new Server(game));
        client = new Thread(new Client(game));
        highScores = false;
    }

    public void reset() {
        highScores = false;
        quitServer();
        quitClient();
        game.reset();
    }

    public Game getGame() {
        return game;
    }

    public void startServer() {
        server.start();
    }

    public void startClient() {
        client.start();
    }

    public void quitServer() {
        server = new Thread(new Server(game));
    }

    public void quitClient() {
        client = new Thread(new Client(game));
    }

    public boolean isHighScores() {
        return highScores;
    }

    public void setHighScores(boolean highScores) {
        this.highScores = highScores;
    }
}
