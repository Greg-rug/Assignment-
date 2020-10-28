import java.io.Serializable;

/**
 * The Score class allows us to make an object that contains the name
 * and points of a player , thus allowing us to store them in an arraylist
 * for future use . Implementing serializable allows the project to sort this
 * type of data .
 */
public class Score  implements Serializable {
    private int points;
    private String name;

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public Score(String name, int points) {
        this.points = points;
        this.name = name;
    }
}