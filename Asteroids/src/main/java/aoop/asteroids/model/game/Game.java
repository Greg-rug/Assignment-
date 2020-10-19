package aoop.asteroids.model.game;

import aoop.asteroids.control.GameUpdater;
import aoop.asteroids.game_observer.ObservableGame;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class is the main model for the Asteroids game. It contains all game objects, and has methods to start and stop
 * the game.
 *
 * This is strictly a model class, containing only the state of the game. Updates to the game are done in
 * {@link GameUpdater}, which runs in its own thread, and manages the main game loop and physics updates.
 */
public class Game extends ObservableGame {

	private Spaceship ship;
	/**
	 * The list of all bullets currently active in the game.
	 */
	private Collection<Bullet> bullets;

	/**
	 * The list of all asteroids in the game.
	 */
	private Collection<Asteroid> asteroids;

	/**
	 * The list of all spaceships. First one is the player, next are other players
	 */
	private ArrayList<Spaceship> spaceships;

	/**
	 * Indicates whether or not the game is running. Setting this to false causes the game to exit its loop and quit.
	 */
	private volatile boolean running = false;

	private boolean asteroidsOnly;

	/**
	 * The game updater thread, which is responsible for updating the game's state as time goes on.
	 */
	private Thread gameUpdaterThread;

	/**
	 * Constructs a new game, with a new spaceship and all other model data in its default starting state.
	 */
	public Game() {
		initializeGameData();
		asteroidsOnly = true;
	}

	/**
	 * Initializes all of the model objects used by the game. Can also be used to reset the game's state back to a
	 * default starting state before beginning a new game.
	 */
	public void initializeGameData() {
		bullets = new ArrayList<>();
		asteroids = new ArrayList<>();
		spaceships = new ArrayList<>();
		//Spaceship ship = new Spaceship();
		ship = new Spaceship();
		ship.reset();
		spaceships.add(ship);
	}

	/**
	 * @return The game's spaceship.
	 */
	public Spaceship getSpaceship() {
		if (spaceships.size() > 0) return ship;
		return null;
	}

	/**
	 * @return The collection of asteroids in the game.
	 */
	public Collection<Asteroid> getAsteroids() {
		return asteroids;
	}

	/**
	 * @return The collection of bullets in the game.
	 */
	public Collection<Bullet> getBullets () {
		return bullets;
	}

	public ArrayList<Spaceship> getSpaceships() {
		return spaceships;
	}

	public boolean isAsteroidsOnly() {
		return asteroidsOnly;
	}

	public void setAsteroidsOnly(boolean asteroidsOnly) {
		this.asteroidsOnly = asteroidsOnly;
	}

	/**
	 * @return Whether or not the game is running.
	 */
	public synchronized boolean isRunning() {
		return running;
	}

	/**
	 * @return True if the player's ship has been destroyed, or false otherwise.
	 */
	public boolean isGameOver() {
		return getSpaceship().isDestroyed();
	}

	/**
	 * Using this game's current model, spools up a new game updater thread to begin a game loop and start processing
	 * user input and physics updates. Only if the game isn't currently running, that is.
	 */
	public void start() {
		if (!running) {
			running = true;
			gameUpdaterThread = new Thread(new GameUpdater(this));
			gameUpdaterThread.start();
		}
	}

	/**
	 * Tries to quit the game, if it is running.
	 */
	public void quit() {
		if (running) {
			try { // Attempt to wait for the game updater to exit its game loop.
				gameUpdaterThread.join(100);
			} catch (InterruptedException exception) {
				System.err.println("Interrupted while waiting for the game updater thread to finish execution.");
			} finally {
				running = false;
				gameUpdaterThread = null; // Throw away the game updater thread and let the GC remove it.
			}
		}
	}
}
