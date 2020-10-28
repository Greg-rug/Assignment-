
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreDB implements ScoreDao {
    @Override
    public void createHighScoreTable() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            statement.execute(" CREATE TABLE IF NOT EXISTS ScoreDB .  HighScore " +
                    "( id int primary key unique auto_increment,"
                    +"name VARCHAR(255) NOT NULL, points INT ) ENGINE = INNODB;");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

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
            System.out.println("INSERT INTO HighScore (name,points)" +
                    "VALUES (?, ?)");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Score> selectAll() {
        List<Score> scores = new ArrayList<Score>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM HighScore");

            while (resultSet.next()) {
                Score score = new Score();
                score.setId(resultSet.getInt("id"));
                score.setName(resultSet.getString("name"));
                score.setPoints(resultSet.getInt("points"));

                scores.add(score);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return scores;
    }

    public List<Score> selectDesc() {
        List<Score> scores = new ArrayList<Score>();
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

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return scores;
    }


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

            System.out.println("UPDATE HighScore SET " +
                    "points = ? WHERE id = ?");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String [] args) {
        ScoreDB pdi = new ScoreDB();

        /**Create table**/
        pdi.createHighScoreTable();

        /**Insert a new record**/
        Score score = new Score("John",1234);
        pdi.insert(score);
        Score score1 = new Score("Jane",3043);
        pdi.insert(score1);
        Score score2 = new Score("John123",120);
        pdi.insert(score2);
        Score score3 = new Score("Kate",1234);
        pdi.insert(score3);
        Score score4 = new Score("Lewis",1000);
        pdi.insert(score4);


        /**Update person**/
        Score personUpdate = new Score("John",2000);
        pdi.update(personUpdate,1);

        /**Select all persons
        List<Score> scores = pdi.selectAll();
        for(Score p : scores) {
            System.out.println(p.getName()+", "+p.getPoints());
        }
         **/
        /**Select all persons ordered by how many points they got**/
         List<Score> scores = pdi.selectDesc();
         for(Score p : scores) {
         System.out.println(p.getName()+", "+p.getPoints());
         }

         }
}