public class Main {
    public static void main(String[] args) {
        ScoreManager hm = new ScoreManager();
        hm.addPoints("Allen",1000);
        hm.addPoints("Margie",367);
        hm.addPoints("Margot",2);
        hm.addPoints("Mona",150);
        hm.addPoints("DiCaprio",2231);

        System.out.print(hm.getHighscore());
    }
}