
import java.util.Comparator;

/**
 * This class is used so that the program is able to compare two points type objects from
 * a pair of provided score data . We implement comparator to order the objects stored in
 * the scores arraylist .
 */
public class ScoreComparator implements Comparator<Score> {
    /**
     * This method is used in order to make the comparison between two score data sets .
     * @param score1 is the data for one user .
     * @param score2 is the data for another user .
     * @return -1 if the first score is greater than the second ;
     * 1 if the first score is lesser than the second ;
     * 0 if both scores are equal .
     */
    public int compare(Score score1, Score score2) {

        int point1 = score1.getPoints();
        int point2 = score2.getPoints();

        if (point1 > point2){
            return -1;
        }else if (point1 < point2){
            return 1;
        }else{
            return 0;
        }
    }
}