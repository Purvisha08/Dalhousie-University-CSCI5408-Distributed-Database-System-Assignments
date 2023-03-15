import javax.xml.stream.events.Comment;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class T1 extends Thread{
    LocalMySQLConnection localMySQLConnection = new LocalMySQLConnection();
    String T_ID;
    String START_TRANSACTION = "START TRANSACTION;";
    String COMMIT = "Commit";
    int TRANSMITTER_ID;
    Connection connection;
    Two_PLP two_plp = new Two_PLP();
    public T1(String T_ID, int TRANSMITTER_ID) throws SQLException {
        this.T_ID = T_ID;
        this.TRANSMITTER_ID = TRANSMITTER_ID;
        connection = localMySQLConnection.createConnection();
    }

    @Override
    public void run() {
        try {
            ResultSet rs1 = null;
            ResultSet rs2 = null;
            ResultSet rs3 = null;
            connection.setAutoCommit(false);
            Statement st1 = connection.createStatement();
            Statement st2 = connection.createStatement();
            Statement st3 = connection.createStatement();
            Statement st4 = connection.createStatement();
            Statement st5 = connection.createStatement();
            st1.execute(START_TRANSACTION);
            st2.execute(START_TRANSACTION);
            st3.execute(START_TRANSACTION);
            System.out.println("Transaction "+T_ID+" Is Executing");
            two_plp.acquireLock();

            System.out.println("Transcation "+T_ID+" locked Table Successfully");
            String readQuery1 = "select detection_serial_number from detections where transmitter_id = "+TRANSMITTER_ID;
            String readQuery2 = "select longitude from detections where transmitter_id = "+TRANSMITTER_ID;
            String readQuery3 = "select latitude from detections where transmitter_id = "+TRANSMITTER_ID;
            String updateQuery = "update detections SET longitude = 876 where transmitter_id =" + TRANSMITTER_ID;
            String deleteQuery = "DELETE FROM detections WHERE transmitter_id=" + TRANSMITTER_ID;

            rs1 = st1.executeQuery(readQuery1);
            while(rs1.next()){
                String columnValue = rs1.getString(1);
                System.out.println("Detection Serial Number for transmitter_id = 123 is - "+columnValue);
            }

            rs2 = st2.executeQuery(readQuery2);
            while(rs2.next()){
                String columnValue = rs2.getString(1);
                System.out.println("longitude for transmitter_id = 123 is - "+columnValue);
            }

            rs3 = st3.executeQuery(readQuery3);
            while(rs3.next()){
                String columnValue = rs3.getString(1);
                System.out.println("latitude for transmitter_id = 123 is - "+columnValue);
            }

            System.out.println("----------------------------");
            System.out.println("3 Data Read Successfully by Transaction " + T_ID);
            st4.executeUpdate(updateQuery);
            System.out.println("1 Data Updated Successfully by Transaction " + T_ID);
            st5.executeUpdate(deleteQuery);
            System.out.println("1 Data deleted Successfully by Transaction "+T_ID);
            System.out.println("----------------------------");

            st1.execute(COMMIT);
            st2.execute(COMMIT);
            st3.execute(COMMIT);
            st4.execute(COMMIT);
            st5.execute(COMMIT);

            two_plp.releaseLock();
            System.out.println("Transcation "+T_ID+" Unlocked Table Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
