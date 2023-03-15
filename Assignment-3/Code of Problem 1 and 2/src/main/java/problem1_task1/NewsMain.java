package problem1_task1;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class NewsMain {
    public static void main(String[] args) {
        try {
            NewsExtraction extractionEngine = new NewsExtraction();
            extractionEngine.extractNews();

            System.setProperty("java.net.preferIPv4Stack", "true");
            Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
            mongoLogger.setLevel(Level.SEVERE);

            NewsFiltration filtrationEngine = new NewsFiltration();
            filtrationEngine.filteringNewsArticles();

            MapReduce mapReduce = new MapReduce();
            mapReduce.mapReduce();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}