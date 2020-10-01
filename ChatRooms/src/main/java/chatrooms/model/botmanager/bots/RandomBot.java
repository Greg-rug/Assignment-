package chatrooms.model.botmanager.bots;

import chatrooms.model.botmanager.Bot;

import java.util.ArrayList;

/**
 * This is a class for bot that sends messages composed for previous received messages
 */
public class RandomBot extends Bot {

    /**
     * Minimal length of the sentence composed by bot
     */
    private final static int MIN_LENGTH = 5;

    /**
     * Maximal length of the sentence composed by bot
     */
    private final static int MAX_LENGTH = 10;

    /**
     * Depth of how many previous messages bot considers when composing new message
     */
    private final static int MAX_DEPTH = 5;

    /**
     * Constructor
     * @param name of bot
     * @param isLocalBot if true bot is local, if false bot is migratory
     */
    public RandomBot(String name, boolean isLocalBot) {
        super(name, isLocalBot);
    }

    /**
     * @return sentence randomly composed from the previous sentences
     */
    @Override
    protected String nextString() {
        ArrayList<String> messages = messageFeed.getLastElements(random.nextInt(MAX_DEPTH) + 1);
        StringBuilder line = new StringBuilder();
        for (String s: messages) {
            line.append(s).append(' ');
        }
        line = new StringBuilder(line.toString().replaceAll("BOT ", ""));
        String[] words = line.toString().split("\\W+");
        StringBuilder sentence = new StringBuilder(words[random.nextInt(words.length)] + ' ');
        sentence = new StringBuilder((sentence.charAt(0) + "").toUpperCase() + sentence.substring(1));
        int sentenceLength = random.nextInt(MAX_LENGTH - MIN_LENGTH + 1) + MIN_LENGTH;
        for (int i = 1; i < sentenceLength; i++){
            sentence.append(words[random.nextInt(words.length)]);
            if (i < sentenceLength - 1) sentence.append(' ');
        }
        sentence.append('.');
        return sentence.toString();
    }

    /**
     * @return greeting of the bot
     */
    @Override
    protected String greeting() {
        return "I am " + name + " and I shall talk your ear off with random nonsense";
    }
}
