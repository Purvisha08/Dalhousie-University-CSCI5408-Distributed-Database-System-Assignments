import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Two_PLP {

    LocalMySQLConnection localMySQLConnection = new LocalMySQLConnection();
    Connection connection;

    public Two_PLP() throws SQLException {
        connection = localMySQLConnection.createConnection();
    }

    public void acquireLock() throws SQLException {
        Statement st = connection.createStatement();
        st.execute("LOCK TABLES projects Read;");
    }

    public void operation() throws Exception {

        int transmiterID1 = 123;
        int transmiterID2 = 888;

        Thread t1 = new Thread(new T1("T1",transmiterID1));
        Thread t2 = new Thread(new T2("T2",transmiterID2));

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    public void releaseLock() throws SQLException {
        Statement st = connection.createStatement();
        st.execute("UNLOCK TABLES;");
    }



}
