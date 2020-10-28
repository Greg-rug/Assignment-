package aoop.asteroids.database;

import java.sql.DriverManager;
import java.sql.Connection;

public class ConnectionConfiguration {

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/ScoreDB", "root", "PassW0rd");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

}
