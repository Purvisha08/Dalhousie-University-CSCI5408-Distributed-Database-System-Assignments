import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        try {

            long t1 = 0;
            long t2 = 0;

            t1 = System.currentTimeMillis();

            Local_Query localDatabaseQuery = new Local_Query();
            GCP_Query gcpDatabaseQuery = new GCP_Query();

            System.out.println();
            localDatabaseQuery.beginTransaction();
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
            localDatabaseQuery.selectQuery();
            localDatabaseQuery.updateQuery();

            System.out.println();
            gcpDatabaseQuery.beginTransaction();
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
            gcpDatabaseQuery.selectQuery();
            gcpDatabaseQuery.updateQuery();

            localDatabaseQuery.commit();
            gcpDatabaseQuery.commit();

            t2 = System.currentTimeMillis();

            System.out.println();
            System.out.println("Transaction Time in local Machine: " + t1 +" milliseconds");
            System.out.println("Transaction Time in Google Cloud Machine: " + t2 +" milliseconds");
            System.out.println("Total Time of transaction is: " + ((t2 - t1)) +" milliseconds");

        } catch (Exception e) {

            e.printStackTrace();

        }


    }

}