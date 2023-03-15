import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MYSQLLocal_Connection {
    public Connection createConnection() throws SQLException {
        String MSQL_USERNAME = "root";
        String MSQL_PASSWORD = "Purvy@0822";
        String MSQL_DATABASE_NAME = "a2_dist<b00912611>";
        String CONNECTION_STRING = "jdbc:mysql://127.0.0.1:3306/" + MSQL_DATABASE_NAME + "?useSSL=false";
        Connection connection = DriverManager.getConnection(CONNECTION_STRING,MSQL_USERNAME,MSQL_PASSWORD);
        System.out.println("Connected Successfully to the Local Database");
        return connection;
    }
}

