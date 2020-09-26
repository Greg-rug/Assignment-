package chatrooms.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MessageFeed {

    private String message;
    private final PropertyChangeSupport changeSupport;

    public MessageFeed() {
        this.changeSupport = new PropertyChangeSupport(this);
    }

    public String getMessage() {
        return message;
    }

    /**
     * Creates a new PropertyChangeEvent every time the message is updated. It then calls firePropertyChange with this event
     * @param message the new message
     */
    public synchronized void setMessage(String message) {
        PropertyChangeEvent messageEvent = new PropertyChangeEvent(this, "message", this.message, message);
        this.message = message;
        changeSupport.firePropertyChange(messageEvent);
    }

    /**
     * Method used to add PropertyChangeListeners to changeSupport
     * @param listener listener to be added to changeSupport
     */
    public void addListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }
}
