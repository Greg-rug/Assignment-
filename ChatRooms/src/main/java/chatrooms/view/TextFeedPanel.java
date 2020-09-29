package chatrooms.view;

import chatrooms.model.Feed;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TextFeedPanel extends JPanel implements PropertyChangeListener {

    private final Feed<String> messageFeed;
    private final JTextArea outputTextArea;

    private final int ROWS = 23;
    private final int COLUMNS = 23;
    private final int GAP = 3;


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

    private JPanel putInTitledScrollPane(JComponent component, String title) {
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBorder(BorderFactory.createTitledBorder(title));
        wrapperPanel.add(new JScrollPane(component));
        return wrapperPanel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        outputTextArea.append(messageFeed.getLast() + "\n");
    }
}
