package aoop.asteroids.model;

import aoop.asteroids.model.game.Game;
import aoop.asteroids.model.online.Client;
import aoop.asteroids.model.online.Server;

/**
 * This class groups the game and it's online features
 */
public class GameServer {

    private boolean highScores;

    private final Game game;

    private Thread server;

    private Thread client;

    /**
     * Constructor
     */
    public GameServer() {
        game = new Game();
        server = new Thread(new Server(game));
        client = new Thread(new Client(game));
        highScores = false;
    }

    /**
     * resets game server to the default state
     */
    public void reset() {
        highScores = false;
        quitServer();
        quitClient();
        game.reset();
    }

    /**
     * @return game
     */
    public Game getGame() {
        return game;
    }

    /**
     * start server
     */
    public void startServer() {
        server.start();
    }

    /**
     * starts client
     */
    public void startClient() {
        client.start();
    }

    /**
     * quits server
     */
    public void quitServer() {
        server.interrupt();
        server = new Thread(new Server(game));
    }

    /**
     * quits client
     */
    public void quitClient() {
        client.interrupt();
        client = new Thread(new Client(game));
    }

    /**
     * @return highscores flag value
     */
    public boolean isHighScores() {
        return highScores;
    }

    /**
     * setter for highscores flag
     * @param highScores value to be set
     */
    public void setHighScores(boolean highScores) {
        this.highScores = highScores;
    }
}
