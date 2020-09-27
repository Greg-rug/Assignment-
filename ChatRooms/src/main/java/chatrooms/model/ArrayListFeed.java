package chatrooms.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;

public class ArrayListFeed {

    private ArrayList<Integer> arrayList;
    private final PropertyChangeSupport changeSupport;
    private static final Random r = new Random();

    public ArrayListFeed() {
        arrayList = new ArrayList<>();
        changeSupport = new PropertyChangeSupport(this);
    }

    public synchronized int getRandom() {
        if (arrayList.size() == 0) return -1;
        return arrayList.get(r.nextInt(arrayList.size()));
    }

    public synchronized void add(int number) {
        ArrayList<Integer> add = arrayList;
        add.add(number);
        PropertyChangeEvent event = new PropertyChangeEvent(this, "add", arrayList, add);
        arrayList = add;
        changeSupport.firePropertyChange(event);
    }

    public synchronized void delete(Integer number) {
        ArrayList<Integer> del = arrayList;
        del.add(number);
        PropertyChangeEvent event = new PropertyChangeEvent(this, "add", arrayList, del);
        arrayList = del;
        changeSupport.firePropertyChange(event);
    }

    public void addListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }
}
