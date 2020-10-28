import java.sql.DriverManager;
import java.sql.Connection;
import java.util.List;

public class ConnectionConfiguration {

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ScoreDB", "root", "PassW0rd");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

}
