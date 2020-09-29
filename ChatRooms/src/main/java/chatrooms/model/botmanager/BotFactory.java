package chatrooms.model.botmanager;

import chatrooms.model.botmanager.bots.NormalBot;
import chatrooms.model.botmanager.bots.RandomBot;
import chatrooms.model.botmanager.bots.RepeatBot;

import java.util.ArrayList;
import java.util.Random;

public class BotFactory {

    private static final Random random = new Random();
    private static final int NUMBER_OF_BOT_TYPES = 3;

    private final ArrayList<String> names;

    public BotFactory() {
        names = BotNames.getAllBotNames();
    }

    public Bot getRandomBot() {
        switch (random.nextInt(NUMBER_OF_BOT_TYPES)) {
            case 0: return getBot("Normal");
            case 1: return getBot("Repeat");
            case 2: return getBot("Random");
        }
        return null;
    }

    private Bot getBot(String type) {
        boolean isLocalBot = random.nextBoolean();
        String name = getRandomName();
        names.remove(name);
        switch (type) {
            case "Normal": return new NormalBot(name, isLocalBot);
            case "Repeat": return new RepeatBot(name, isLocalBot);
            case "Random": return new RandomBot(name, isLocalBot);
        }
        return null;
    }

    private String getRandomName() {
        if (names.size() > 0) return names.get(random.nextInt(names.size()));
        return "Unnamed";
    }
}
