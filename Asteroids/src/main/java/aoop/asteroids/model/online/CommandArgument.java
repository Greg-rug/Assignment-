package aoop.asteroids.model.online;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * This is an enum for networking command and it allows for an easier binding of the signal commands to a hashmap
 */
public enum CommandArgument {

    JOIN_SIGNAL (true, "Join signal", 0),
    SPECTATE_SIGNAL (true, "Spectate signal", 1),
    MAINTAIN_SIGNAL (true, "Maintain signal", 2);

    private final boolean isArgument;
    private final String title;
    private final int ID;

    /**
     * Constructor
     * @param isArgument if true it exists and is in use , otherwise it is neither
     * @param title of the menu item
     * @param ID of the menu item
     */
    CommandArgument(boolean isArgument, String title, int ID) {
        this.isArgument = isArgument;
        this.title = title;
        this.ID = ID;
    }

    /**
     * @return ArrayList of all networking commands
     */
    public static ArrayList<CommandArgument> getAllArguments() {
        ArrayList<CommandArgument> arguments = new ArrayList<>();
        IntStream.range(0, values().length).forEach(i -> arguments.add(values()[i]));
        return arguments;
    }

    /**
     * @return ArrayList of all strictly networking commands
     */
    public static ArrayList<CommandArgument> getCommandArguments() {
        ArrayList<CommandArgument> arguments = new ArrayList<>();
        IntStream.range(0, values().length).forEach(i -> {
            if (values()[i].isArgument()) arguments.add(values()[i]);
        });
        return arguments;
    }

    /**
     * @return Number of networking commands
     */
    public static int getNumberOfArguments() {
        return (int) IntStream.range(0, values().length).filter(i -> values()[i].isArgument()).count();
    }

    /**
     * finds a networking command by it's name
     * @param title of the command
     * @return corresponding command argument if it exist, null otherwise
     */
    public static CommandArgument findArguments(String title) {
        if (title == null) return null;
        for (int i = 0; i < values().length; i++) {
            if (values()[i].title.equals(title)) return values()[i];
        }
        return null;
    }

    /**
     * @return false if the networking command is not implemented , true otherwise
     */
    public boolean isArgument() {
        return isArgument;
    }

    /**
     * @return title of a networking command
     */
    public String getTitle() {
        return title;
    }


    /**
     * @return ID of a networking command
     */
    public int getID() {
        return ID;
    }
}
