package chatrooms.model.botmanager;

import java.beans.PropertyChangeEvent;

public class NormalBot extends Bot {

    public NormalBot(String name, boolean isLocalBot) {
        super(name, isLocalBot);
    }

    @Override
    public String nextString() {
        return ("Hello, my name is " + name + " and I am a normal bot.");
    }

}
