package chatrooms.model.botmanager;

import chatrooms.model.Feed;
import chatrooms.model.botmanager.bots.NormalBot;
import chatrooms.model.botmanager.bots.RandomBot;
import chatrooms.model.botmanager.bots.RepeatBot;

import java.util.Random;

/**
 * factory for bots
 */
public class BotFactory {

    /**
     * Number of bots that are implemented
     */
    private static final int NUMBER_OF_BOT_TYPES = 3;

    /**
     * Normal bot string
     */
    private static final String NORMAL_BOT = "Normal";

    /**
     * Repeat bot string
     */
    private static final String REPEAT_BOT = "Repeat";

    /**
     * Random bot string
     */
    private static final String RANDOM_BOT = "Random";

    /**
     * Default name for bot
     */
    private static final String DEFAULT_NAME = "Unnamed";

    private static final Random random = new Random();

    private final Feed<String> names;

    /**
     * Constructor - initialises collection of bot names
     */
    public BotFactory() {
        names = BotNames.getAllBotNames();
    }

    /**
     * @return random bot
     */
    public Bot getRandomBot() {
        switch (random.nextInt(NUMBER_OF_BOT_TYPES)) {
            case 0: return getBot(NORMAL_BOT);
            case 1: return getBot(REPEAT_BOT);
            case 2: return getBot(RANDOM_BOT);
        }
        return null;
    }

    /**
     * @param type of BOT
     * @return bot if it's type exists
     */
    public Bot getBot(String type) {
        boolean isLocalBot = random.nextBoolean();
        String name = getRandomName();
        names.remove(name);
        switch (type) {
            case NORMAL_BOT: return new NormalBot(name, isLocalBot);
            case REPEAT_BOT: return new RepeatBot(name, isLocalBot);
            case RANDOM_BOT: return new RandomBot(name, isLocalBot);
        }
        return null;
    }

    /**
     * @return random name from bot names and removes it from names
     */
    private String getRandomName() {
        if (names.size() > 0)  {
            String name = names.get(random.nextInt(names.size()));
            names.remove(name);
            return name;
        }
        return DEFAULT_NAME;
    }
}
