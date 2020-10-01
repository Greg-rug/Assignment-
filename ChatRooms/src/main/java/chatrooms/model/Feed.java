package chatrooms.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * This is a extended arraylist to use with multiple threads that calls property change listener for each change
 * and with some added handy methods
 * @param <E> Object type to be stored in Feed
 */
public class Feed<E> extends ArrayList<E> {

    private static final Random random = new Random();

    private final PropertyChangeSupport changeSupport;

    /**
     * Constructor
     */
    public Feed() {
        super();
        changeSupport = new PropertyChangeSupport(this);
    }

    /**
     * @param index of the element
     * @return the element on the index
     */
    @Override
    public synchronized E get(int index) {
        return super.get(index);
    }

    /**
     * @return last element in the list
     */
    public synchronized E getLast() {
        if (size() == 0) return null;
        return get(size()-1);
    }

    /**
     * @param x number of last elements
     * @return x last elements in the list
     */
    public ArrayList<E> getLastElements(int x) {
        ArrayList<E> lastMessages = new ArrayList<>();
        int start = Math.max(size() - x, 0);
        IntStream.range(start, size()).forEach(i -> lastMessages.add(get(i)));
        return lastMessages;
    }

    /**
     * @return random element from the list
     */
    public synchronized E getRandom() {
        if (size() == 0) return null;
        return get(random.nextInt(size()));
    }

    /**
     * add element to the list
     * @param element to be added
     * @return true if successfully added
     */
    @Override
    public synchronized boolean add(E element) {
        boolean outcome = super.add(element);
        if (outcome) {
            PropertyChangeEvent event = new PropertyChangeEvent(this, "addElement",
                    null, null);
            changeSupport.firePropertyChange(event);
        }
        return outcome;
    }

    /**
     * removes object from the list
     * @param o object to be removed
     * @return true if successfully removed
     */
    @Override
    public synchronized boolean remove(Object o) {
        boolean outcome = super.remove(o);
        if (outcome) {
            PropertyChangeEvent event = new PropertyChangeEvent(this, "removeElement",
                    null, null);
            changeSupport.firePropertyChange(event);
        }
        return outcome;
    }

    /**
     * removes element on index
     * @param index to remove element on
     * @return true if successfully removed
     */
    @Override
    public synchronized E remove(int index) {
        if (index >= 0 && index < size()) {
            PropertyChangeEvent event = new PropertyChangeEvent(this, "removeElement",
                    null, null);
            changeSupport.firePropertyChange(event);
            return super.remove(index);
        }
        return null;
    }

    /**
     * adds listener  to changeSupport
     * @param listener to be added
     */
    public void addListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }
}
