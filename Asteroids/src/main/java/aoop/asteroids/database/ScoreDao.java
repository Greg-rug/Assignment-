package aoop.asteroids.database;

import java.util.List;

public interface ScoreDao {
    void createHighScoreTable();

    void insert(Score score);

    List<Score> selectAll();

    List<Score> selectDesc() ;

    void update(Score score,int id);
}
