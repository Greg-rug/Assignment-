package chatrooms.model.botmanager.bots;

import chatrooms.model.botmanager.Bot;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomBot extends Bot {

    private final static int MIN_LENGTH = 5;
    private final static int MAX_LENGTH = 10;
    private final static int MAX_DEPTH = 5;
    private static final Random random = new Random();

    public RandomBot(String name, boolean isLocalBot) {
        super(name, isLocalBot);
    }

    @Override
    protected String nextString() {
        ArrayList<String> messages = messageFeed.getLastElements(random.nextInt(MAX_DEPTH) + 1);
        String line = "";
        for (String s: messages) {
            line += s + ' ';
        }
        line = line.replaceAll("BOT ", "");
        String[] words = line.split("\\W+");

        String sentence = words[random.nextInt(words.length)] + ' ';
        sentence = (sentence.charAt(0) + "").toUpperCase() + sentence.substring(1);
        int sentenceLength = random.nextInt(MAX_LENGTH - MIN_LENGTH + 1) + MIN_LENGTH;
        for (int i = 1; i < sentenceLength; i++){
            sentence += words[random.nextInt(words.length)];
            if (i < sentenceLength - 1) sentence += ' ';
        }
        sentence += '.';
        return sentence;
    }

    @Override
    protected String greeting() {
        return "I am " + name + " and I shall talk your ear off with random nonsense";
    }
}
