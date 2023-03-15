package problem1_task2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MoviesMain {
    public static void main(String[] args) {
        try {
            MovieDbExtraction moviesExtraction = new MovieDbExtraction();
            moviesExtraction.extractMovies();

            System.setProperty("java.net.preferIPv4Stack", "true");
            Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
            mongoLogger.setLevel(Level.SEVERE);

            MovieDbFiltration movieFiltration = new MovieDbFiltration();
            movieFiltration.filteringNewsArticles();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
