package aoop.asteroids.model.online;

import aoop.asteroids.model.online.NetworkCommands.JoinSignalCommand;
import aoop.asteroids.model.online.NetworkCommands.MaintainSignalCommand;
import aoop.asteroids.model.online.NetworkCommands.SpectateSignalCommand;

import java.util.HashMap;

/**
 * This is a class of command handler for networking commands
 */
public class CommandHandler {

    private final HashMap<CommandArgument, CommandInterface> CommandsMap;
    private final HashMap<String, CommandArgument> stringCommandMap;

    /**
     * Constructor
     * @param servers sever meant to implement connection with multiple users
     */
    public CommandHandler(Server servers) {
        CommandsMap = new HashMap<>();
        stringCommandMap = new HashMap<>();
        for (CommandArgument argument: CommandArgument.getAllArguments()) {
            stringCommandMap.putIfAbsent(argument.toString(), argument);
        }
        
        CommandsMap.putIfAbsent(CommandArgument.JOIN_SIGNAL, new JoinSignalCommand(servers));
        CommandsMap.putIfAbsent(CommandArgument.SPECTATE_SIGNAL, new SpectateSignalCommand(servers));
        CommandsMap.putIfAbsent(CommandArgument.MAINTAIN_SIGNAL, new MaintainSignalCommand(servers));
        

    /**
     * @param command to be mapped
     * @return CommandArgument if it exists
     */
    public CommandInterface getCommand(CommandArgument command) {
        return CommandsMap.get(command);
    }

    /**
     * @param command to be mapped
     * @return CommandInterface if it exists
     */
    public CommandInterface getCommand(String command) {
        CommandArgument argument = stringCommandMap.get(command);
        if (argument != null) return stringCommandMap.get(argument);
        return null;
    }

  }
}
