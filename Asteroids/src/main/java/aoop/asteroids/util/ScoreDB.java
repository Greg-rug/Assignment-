package aoop.asteroids.database;

import aoop.asteroids.model.game.Score;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreDB implements ScoreDao {

    /**
     * creates a table for scores in the database
     */
    @Override
    public void createHighScoreTable() {
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            statement.execute(" CREATE TABLE IF NOT EXISTS ScoreDB .  HighScore " +
                    "( id int primary key unique auto_increment,"
                    +"name VARCHAR(255) NOT NULL, points INT ) ENGINE = INNODB;");
        } catch (SQLException e) {
            System.out.println("Database error.");
        } finally {
            close(statement);
            close(connection);
        }
    }

    /**
     * insert score to the database
     * @param score to be inserted
     */
    @Override
    public void insert(Score score) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO HighScore (name,points)" +
                    "VALUES (?, ?)");
            preparedStatement.setString(1, score.getName());
            preparedStatement.setInt(2, score.getPoints());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error.");
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    /**
     * selects all scores by descending order
     * @return
     */
    @Override
    public List<Score> selectAllDesc() {
        List<Score> scores = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM HighScore ORDER BY points DESC");
            while (resultSet.next()) {
                Score score = new Score();
                score.setId(resultSet.getInt("id"));
                score.setName(resultSet.getString("name"));
                score.setPoints(resultSet.getInt("points"));
                scores.add(score);
            }
        } catch (SQLException e) {
            System.out.println("Database error.");
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return scores;
    }

    /**
     * updates score by id
     * @param score to update
     * @param id of updated score
     */
    @Override
    public void update(Score score, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE HighScore SET " +
                    "name=?, points = ? WHERE id = ?");
            preparedStatement.setString(1,score.getName());
            preparedStatement.setInt(2, score.getPoints());
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Database error.");
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    /**
     * closes statement
     * @param statement to be closed
     */
    private void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Database problem.");
            }
        }
    }

    /**
     * closes connection
     * @param connection to be closed
     */
    private void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Database connection problem, cannot close connection.");
            }
        }

    }

    /**
     * closes result set
     * @param resultSet to be closed
     */
    private void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Database problem.");
            }
        }
    }
}
