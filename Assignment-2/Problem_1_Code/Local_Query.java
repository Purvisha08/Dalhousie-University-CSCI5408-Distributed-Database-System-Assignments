import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Local_Query {

    MYSQLLocal_Connection mysqlLocalConnection = new MYSQLLocal_Connection();
    Connection connection;

    public Local_Query() throws SQLException {
        connection = mysqlLocalConnection.createConnection();
    }

    String BEGIN_TRANSACTION = "BEGIN TRANSACTION";
    String SELECT_QUERY = "SELECT capacity FROM room WHERE capacity = '2 Adults';;";
    String UPDATE_QUERY = "UPDATE room SET rate = 1000 WHERE room_id = 80;";
    String COMMIT = "Commit";

    public void beginTransaction() throws SQLException{
        connection.setAutoCommit(false);
        String beginTransaction = BEGIN_TRANSACTION;
        PreparedStatement preparedStatement = getPreparedStatement(beginTransaction);
        closePreparedStatement(preparedStatement);
        System.out.println("Succesfully Started Transaction in local");
    }

    public void selectQuery() throws SQLException {
        PreparedStatement preparedStatement = getPreparedStatement(SELECT_QUERY);
        executeQuery(preparedStatement);
        closePreparedStatement(preparedStatement);
        System.out.println("Data fetched Successfully");
    }

    public void updateQuery() throws SQLException {
        PreparedStatement preparedStatement = getPreparedStatement(UPDATE_QUERY);
        executeUpdate(preparedStatement);
        closePreparedStatement(preparedStatement);
        System.out.println("Data Updated Successfully");
    }

    public void commit() throws SQLException {
        String commitTransaction = COMMIT;
        PreparedStatement preparedStatement = getPreparedStatement(commitTransaction);
        closePreparedStatement(preparedStatement);
    }

    private PreparedStatement getPreparedStatement(String beginTransaction) throws SQLException {
        return connection.prepareStatement(beginTransaction);
    }

    private void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
    }

    private void executeQuery(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.executeQuery();
    }

    private void executeUpdate(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.executeUpdate();
    }

}
