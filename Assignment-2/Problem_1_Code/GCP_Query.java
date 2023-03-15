import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GCP_Query {
    GCP_Connection gcpConnection = new GCP_Connection();
    Connection connection;
    public GCP_Query() throws SQLException {
        connection = gcpConnection.createConnection();
    }
    String BEGIN_TRANSACTION = "BEGIN TRANSACTION";
    String SELECT_QUERY = "SELECT address FROM park WHERE address ='Cape Breton';";
    String UPDATE_QUERY = "UPDATE park SET address = 'Cape Breton' WHERE park_name = 'Anthony';";
    String COMMIT = "Commit";

    public void beginTransaction() throws SQLException{
        connection.setAutoCommit(false);
        String beginTransaction =BEGIN_TRANSACTION;
        PreparedStatement preparedStatement = getPreparedStatement(beginTransaction);
        closePreparedStatement(preparedStatement);
        System.out.println("Succesfully Started Transaction on Google Cloud Platform");
    }

    public void selectQuery() throws SQLException {
        PreparedStatement preparedStatement = getPreparedStatement(SELECT_QUERY);
        executeQuery(preparedStatement);
        closePreparedStatement(preparedStatement);
        System.out.println("Successfully Extracted Data from GCP");
    }

    public void updateQuery() throws SQLException {
        PreparedStatement preparedStatement = getPreparedStatement(UPDATE_QUERY);
        executeUpdate(preparedStatement);
        closePreparedStatement(preparedStatement);
        System.out.println("Successfully Extracted Data from GCP");
    }

    public void commit() throws SQLException {
        String commitTransaction = COMMIT;
        PreparedStatement preparedStatement = getPreparedStatement(commitTransaction);
        closePreparedStatement(preparedStatement);
    }

    private void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
    }

    private PreparedStatement getPreparedStatement(String selectQuery) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        return preparedStatement;
    }

    private void executeQuery(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.executeQuery();
    }

    private void executeUpdate(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.executeUpdate();
    }
}
