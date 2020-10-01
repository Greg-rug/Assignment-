package chatrooms.model.botmanager.bots;

import chatrooms.model.botmanager.Bot;

/**
 * This is a class for bot that repeat the previous message
 */
public class RepeatBot extends Bot {

    /**
     * Constructor
     * @param name of bot
     * @param isLocalBot if true bot is local, if false bot is migratory
     */
    public RepeatBot(String name, boolean isLocalBot) {
        super(name, isLocalBot);
    }

    /**
     * @return last message in the message feed without the name of BOT who sent the previous message
     */
    @Override
    public String nextString() {
        String line = messageFeed.getLast();
        if (line != null) {
            int from = 0;
            if (line.contains(": ")) {
                from = line.indexOf(": ") + 2;
            }
            line = line.substring(from);
        }
        return line;
    }

    /**
     * @return greeting of the bot
     */
    @Override
    protected String greeting() {
        return "I am " + name + " and I shall repeat whatever you type";
    }
}
