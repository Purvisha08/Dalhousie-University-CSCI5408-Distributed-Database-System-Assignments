package problem1_task1;

import org.bson.Document;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class NewsFiltration {
    NewsMongoDBConnection mongoDBConnection = new NewsMongoDBConnection();
    public void filteringNewsArticles() {
        File[] files = new File("./newsData/").listFiles();
        if (CheckFileStatus(files)) {
            int numberOfArticleRead = 0;
            List<Document> newsDocumentOnMongoDB = new ArrayList<>();
            System.out.println("Now filtering the news articles....");
            System.out.println("Reading the news articles and removing the unnecessary things...");
            for (File newsFile : files) {
                try {
                    String cleanedContent;
                    cleanedContent = Files.readString(Paths.get(newsFile.getPath())).trim().replaceAll("[^\\p{L}\\p{N}\\p{P}\\p{Z}]", " ")
                            .replaceAll("(\"urlToImage\":(\"http.*?\"|null|\"null\"|\"\"),)", " ")
                            .replaceAll("(\"url\":(\"http.*?\"|null|\"null\"|\"\"),)", " ")
                            .replaceAll("(\"author\":(\"http.*?\"|null|\"null\"|\"\"),)", " ");
                    cleanedContent = cleanedContent.substring(1, cleanedContent.length() - 1)
                            .replace("},{", "}####{");
                    for (String newsArticle : cleanedContent.split("####")) {
                        Document newsArticleDoc = Document.parse(newsArticle);
                        newsDocumentOnMongoDB.add(newsArticleDoc);
                        numberOfArticleRead++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Total number of news articles read and filtered - " + numberOfArticleRead);
            mongoDBConnection.getConnection().getDatabase("myMongoNews").getCollection("mongoNews").insertMany(newsDocumentOnMongoDB);
            System.out.println("News Articles stored successfully on MoongoDB.");
        }
    }
    private boolean CheckFileStatus(File[] files) {
        return files != null && files.length != 0;
    }
}