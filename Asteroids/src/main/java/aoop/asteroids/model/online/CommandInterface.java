package aoop.asteroids.model.online;

/**
 * This is an command interface for network commands
 */
public interface CommandInterface {
    public static final int JOIN_SIGNAL = 1;
    public static final int MAINTAIN_SIGNAL = 2;
    public static final int SPECTATE_SIGNAL = 3;
    /**
     * Executed when CommandHandler calls it
     */
    void execute();
}
