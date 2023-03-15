import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LocalMySQLConnection {
    public Connection createConnection() throws SQLException {
        Connection conn = null;
        String MYSQL_USERNAME = "root";
        String MYSQL_PASSWORD = "12345";
        String MYSQL_DATABASE_NAME = "a1_otn_data_normalized";
        String MYSQL_CONNECTION_STRING = "jdbc:mysql://localhost:3306/" + MYSQL_DATABASE_NAME + "?useSSL=false";
        conn = DriverManager.getConnection(MYSQL_CONNECTION_STRING,MYSQL_USERNAME,MYSQL_PASSWORD);
        return conn;
    }
}
