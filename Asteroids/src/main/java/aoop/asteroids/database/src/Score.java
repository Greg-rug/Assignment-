public class Score {
    private int id;
    private String name;
    private int points;

    public Score(String name, int points){
        this.name = name;
        this.points = points;
    }

    public Score(){
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
