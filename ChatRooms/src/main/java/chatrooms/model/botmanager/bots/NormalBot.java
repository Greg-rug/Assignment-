package chatrooms.model.botmanager.bots;

import chatrooms.model.botmanager.Bot;

public class NormalBot extends Bot {

    public NormalBot(String name, boolean isLocalBot) {
        super(name, isLocalBot);
    }

    @Override
    public String nextString() {
        return ("I am totally normal - " + name);
    }

    @Override
    protected String greeting() {
        return "Hello, I am considered normal!";
    }
}
