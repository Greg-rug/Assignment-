import java.util.*;
import java.io.*;

/**
 * This class is the Score Manager and within it we will use a binary file
 * known as scores.dat to store the score data akin to a database . In order to
 * work with the scores we will employ the use of an arraylist .
 */
public class ScoreManager {
    private ArrayList<Score> scores;
    private static final String HIGHSCORE_FILE = "scores.dat";
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;
    public ScoreManager() {
        scores = new ArrayList<Score>();
    }

    /**
     * This function reads the scores.dat file and uploads it's data into
     * the score arraylist . Meanwhile the try catch is there to prevent the program
     * from crashing if the loading the file goes wrong .
     */
    public void loadScoreFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            scores = (ArrayList<Score>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("[Load] FNF Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[Load] IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("[Load] CNF Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Load] IO Error: " + e.getMessage());
            }
        }
    }

    /**
     * This function writes the score arraylist into the scores.dat file .
     */
    public void updateScoreFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            outputStream.writeObject(scores);
        } catch (FileNotFoundException e) {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
    }

    /**
     * This function calls the loadScoreFile() and sort() functions so that
     * the score data is written in a sorted order into the scores.dat file .
     * @return scores which is an arraylist with the scores data in it.
     */
    public ArrayList<Score> getScores() {
        loadScoreFile();
        sort();
        return scores;
    }

    /**
     * This function creates a new object "compartor" from the ScoreComparator class
     * in order to sort the arraylist scores .
     */
    private void sort() {
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(scores, comparator);
    }

    /**
     * This method is used to add scores data into the scores.dat file
     * @param name of a player .
     * @param points obtained by a player following a game session .
     */
    public void addPoints(String name, int points) {
        loadScoreFile();
        scores.add(new Score(name, points));
        updateScoreFile();
    }

    /**
     * This function is used to assemble the top ten highest scores from the scores arraylist and
     * print out for the user to view .
     * @return HSString is a string that forms the High Score table that contains the name and how many points
     * a player has ordered in a descending manner .
     */
    public String getHighscore() {
        String HSString = "";
        int max = 10;
        int i = 0;
        int x = scores.size();
        ArrayList<Score> scores;
        scores = getScores();
        if (x > max) {
            x = max;
        }
        while (i < x) {
            HSString += (i + 1) + ".\t" + scores.get(i).getName() + "\t\t" + scores.get(i).getPoints() + "\n";
            i++;
        }
        return HSString;
    }
}
