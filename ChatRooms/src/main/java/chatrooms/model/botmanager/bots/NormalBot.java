package chatrooms.model.botmanager.bots;

import chatrooms.model.botmanager.Bot;

/**
 * this is a class for simple bot that is repeating only one string
 */
public class NormalBot extends Bot {

    /**
     * Constructor
     * @param name of bot
     * @param isLocalBot if true bot is local, if false bot is migratory
     */
    public NormalBot(String name, boolean isLocalBot) {
        super(name, isLocalBot);
    }

    /**
     * @return simple string that bot is repeating
     */
    @Override
    public String nextString() {
        return ("I am totally normal - " + name);
    }

    /**
     * @return greeting of the bot
     */
    @Override
    protected String greeting() {
        return "Hello, I am considered normal!";
    }
}
