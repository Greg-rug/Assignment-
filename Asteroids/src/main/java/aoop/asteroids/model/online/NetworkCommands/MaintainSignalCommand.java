package aoop.asteroids.model.online.NetworkCommands;

import aoop.asteroids.model.online.Connection;
import aoop.asteroids.model.online.Server;

/**
 * Command for ensuring that the server behaves according to receiving the maintain signal
 */
public class MaintainSignalCommand extends AbstractCommand {

    /**
     * Constructor
     * @param server server meant to implement the connections in the UDP network
     */
    public MaintainSignalCommand(Server server) {
        super(server);
    }

    /**
     * This execute function continues the run() method from Server class meant for implementing
     * the server behaviour for when the connection listened to receives the
     * maintain signal
     */
    @Override
    public void execute() {
        super.execute();
        server.run();
	    Connection c = server.findConnection(dp.getAddress());
        server.moveSpaceship(bytes);
        if (c != null) {
            c.setLastTick(game.getLastLocalTick());
            c.setRunning(true);
        }
    }
}
