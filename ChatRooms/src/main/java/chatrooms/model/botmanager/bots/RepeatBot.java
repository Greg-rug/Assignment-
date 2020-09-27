package chatrooms.model.botmanager.bots;

import chatrooms.model.botmanager.Bot;

public class RepeatBot extends Bot {

    public RepeatBot(String name, boolean isLocalBot) {
        super(name, isLocalBot);
    }

    @Override
    public String nextString() {
        String line = messageFeed.getMessage();
        if (line != null) {
            int from = 0;
            if (line.contains(": ")) {
                from = line.indexOf(": ") + 2;
            }
            line = line.substring(from);
        }
        return line;
    }

}
