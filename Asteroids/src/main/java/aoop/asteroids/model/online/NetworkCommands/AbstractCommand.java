package aoop.asteroids.model.online.NetworkCommands;

import aoop.asteroids.model.online.CommandInterface;
import aoop.asteroids.model.online.Server;

public abstract class AbstractCommand implements CommandInterface {
    protected final Server server;

    /**
     * Constructor
     * @param server the UDP networking server
     */
    public AbstractMenuCommand(Server server) {
        this.server = server;
    }

}
