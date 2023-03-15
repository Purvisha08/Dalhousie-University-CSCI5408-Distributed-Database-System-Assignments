package problem1_task2;

import org.bson.Document;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MovieDbFiltration {
    MovieMongoDBConnection mongoDBConnection = new MovieMongoDBConnection();
    public void filteringNewsArticles() {
        File[] files = new File("./moviesData/").listFiles();
        if (CheckFileStatus(files)) {
            int numberOfArticleRead = 0;
            List<Document> moviesDocumentOnMongoDB = new ArrayList<>();
            System.out.println("Now filtering the movies articles....");
            System.out.println("Reading the movies and removing the unnecessary things...");
            for (File moviesFile : files) {
                try {
                    String cleanedContent;
                    cleanedContent = Files.readString(Paths.get(moviesFile.getPath())).trim().replaceAll("[^\\p{L}\\p{N}\\p{P}\\p{Z}]", " ");
                    cleanedContent = cleanedContent.substring(1, cleanedContent.length() - 1)
                            .replace("},{", "}####{");
                    for (String moviesArticle : cleanedContent.split("####")) {
                        Document moviesDoc = Document.parse(moviesArticle);
                        moviesDocumentOnMongoDB.add(moviesDoc);
                        numberOfArticleRead++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Total number of movies read and filtered - " + numberOfArticleRead);
            mongoDBConnection.getConnection().getDatabase("myMongoMovieDb").getCollection("mongomovies").insertMany(moviesDocumentOnMongoDB);
            System.out.println("Movies stored successfully on MoongoDB.");
        }
    }
    private boolean CheckFileStatus(File[] files) {
        return files != null && files.length != 0;
    }
}
