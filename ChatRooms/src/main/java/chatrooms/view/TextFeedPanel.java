package chatrooms.view;

import chatrooms.model.Feed;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Panel that shows feed of server or chatroom
 */
public class TextFeedPanel extends JPanel implements PropertyChangeListener {

    /**
     * number of rows
     */
    private static final int ROWS = 30;

    /**
     * number of columns
     */
    private static final int COLUMNS = 30;

    /**
     * gap between borders and output text area
     */
    private static final int GAP = 3;

    private final Feed<String> messageFeed;
    private final JTextArea outputTextArea;

    /**
     * Constructor
     * @param messageFeed to be shown
     * @param title above the text area
     */
    public TextFeedPanel(Feed<String> messageFeed, String title) {
        this.messageFeed = messageFeed;
        messageFeed.addListener(this);
        outputTextArea = new JTextArea(ROWS, COLUMNS);
        outputTextArea.setFocusable(false);
        outputTextArea.setEditable(false);
        setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(putInTitledScrollPane(outputTextArea, title));
    }

    /**
     *
     * @param component to be added the panel with scroll pane
     * @param title above the text area
     * @return the constructed panel
     */
    private JPanel putInTitledScrollPane(JComponent component, String title) {
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBorder(BorderFactory.createTitledBorder(title));
        wrapperPanel.add(new JScrollPane(component));
        return wrapperPanel;
    }

    /**
     * updates the view with the new message
     * @param evt added message to message feed
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        outputTextArea.append(messageFeed.getLast() + "\n");
    }
}
