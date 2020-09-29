package chatrooms.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class Feed<E> extends ArrayList<E> {

    private final PropertyChangeSupport changeSupport;
    private static final Random random = new Random();

    public Feed() {
        super();
        changeSupport = new PropertyChangeSupport(this);
    }

    public synchronized E getLast() {
        if (size() == 0) return null;
        return get(size()-1);
    }

    public ArrayList<E> getLastElements(int x) {
        ArrayList<E> lastMessages = new ArrayList<>();
        int start = Math.max(size() - x, 0);
        IntStream.range(start, size()).forEach(i -> lastMessages.add(get(i)));
        return lastMessages;
    }

    public synchronized boolean add(E newElement) {
        PropertyChangeEvent addEvent = new PropertyChangeEvent(this, "newElement",
                getLast(), newElement);
        boolean outcome = super.add(newElement);
        changeSupport.firePropertyChange(addEvent);
        return outcome;
    }

    public synchronized E getRandom() {
        if (size() == 0) return null;
        return get(random.nextInt(size()));
    }

    public void addListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }
}
