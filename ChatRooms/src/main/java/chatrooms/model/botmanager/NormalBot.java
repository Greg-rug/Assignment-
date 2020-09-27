package chatrooms.model.botmanager;

public class NormalBot extends Bot {

    public NormalBot(String name, boolean isLocalBot) {
        super(name, isLocalBot);
    }

    @Override
    public String nextString() {
        return ("I am totally normal - " + name);
    }

}
