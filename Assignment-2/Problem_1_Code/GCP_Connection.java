import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GCP_Connection {
    public Connection createConnection() throws SQLException {
        String GCP_USERNAME = "root";
        String GCP_PASSWORD = "Purvy@0822";
        String GCP_DATABASE_NAME = "a2_dist<b00912611>";
        String CONNECTION_STRING = "jdbc:mysql://34.123.3.57:3306/" + GCP_DATABASE_NAME + "?useSSL=false";
        Connection connection = DriverManager.getConnection(CONNECTION_STRING,GCP_USERNAME,GCP_PASSWORD);
        System.out.println("Connected Successfully to the GCP Database");
        return connection;
    }
}

