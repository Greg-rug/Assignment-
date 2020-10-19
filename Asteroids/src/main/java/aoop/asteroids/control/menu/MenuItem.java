package aoop.asteroids.control.menu;

import java.util.ArrayList;
import java.util.stream.IntStream;

public enum MenuItem {

    NEW_GAME (true, "New Game", 0),
    JOIN (true, "Join online Game", 1),
    HOST (true, "Host online game", 2),
    SPECTATE (true, "Spectate", 3),
    HIGH_SCORES (true, "High Scores", 4),
    MAIN_MENU (false, "Main Menu", 5),
    QUIT (false, "Quit", 6);

    private final boolean isMenuItem;
    private final String title;
    private final int ID;

    MenuItem(boolean isMenuItem, String title, int ID) {
        this.isMenuItem = isMenuItem;
        this.title = title;
        this.ID = ID;
    }

    public static ArrayList<MenuItem> getAllItems() {
        ArrayList<MenuItem> items = new ArrayList<>();
        IntStream.range(0, values().length).forEach(i -> items.add(values()[i]));
        return items;
    }

    public static ArrayList<MenuItem> getMenuItems() {
        ArrayList<MenuItem> items = new ArrayList<>();
        IntStream.range(0, values().length).forEach(i -> {
            if (values()[i].isMenuItem()) items.add(values()[i]);
        });
        return items;
    }

    public static int getNumberOfMenuItems() {
        return (int) IntStream.range(0, values().length).filter(i -> values()[i].isMenuItem()).count();
    }

    public static MenuItem findMenuItem(String title) {
        if (title == null) return null;
        for (int i = 0; i < values().length; i++) {
            if (values()[i].title.equals(title)) return values()[i];
        }
        return null;
    }

    public boolean isMenuItem() {
        return isMenuItem;
    }

    public String getTitle() {
        return title;
    }

    public int getID() {
        return ID;
    }
}
