package problem2;

import com.mongodb.client.*;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class P2_SentimentAnalysis {
  String Regex1 = "\\[\\d+]";
  String Regex2 = "(\")|(\\{)|(…)";
  ArrayList<String> positiveWordsSet = new ArrayList<>();
  ArrayList<String> negativeWordsSet = new ArrayList<>();
  String negativeWord;
  String positiveWord;
  public List<P2_AnalysisModel> performAnalysis() {
    List<P2_AnalysisModel> analysisModelArrayList = new ArrayList<>();
    Set<String> newsContent = new LinkedHashSet<>();
    try (MongoClient mongoClient = MongoClients.create("mongodb+srv://purvisha99:purvisha99@mymongonews.eqq5b.mongodb.net/mongoNews?retryWrites=true&w=majority")) {
      MongoCursor<Document> mongoCursor = mongoClient.getDatabase("mongoNews").getCollection("myMongoNews").find().iterator();
      getNewsContent(mongoCursor, newsContent);
      for (String content : newsContent) {
        analysisModelArrayList.add(new P2_AnalysisModel(analysisModelArrayList.size() + 1, content));
      }
      while ((positiveWord = new BufferedReader(new FileReader("sentiment_words/positive_words.txt")).readLine()) != null) {
        positiveWordsSet.add(positiveWord);
      }
      while ((negativeWord = new BufferedReader(new FileReader("sentiment_words/negative_words.txt")).readLine()) != null) {
        negativeWordsSet.add(negativeWord);
      }
      analysisModelArrayList.forEach(entry ->
          entry.generatePolarity(negativeWordsSet, positiveWordsSet));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return analysisModelArrayList;
  }

  private void getNewsContent(MongoCursor<Document> mongoCursor, Set<String> newsContent) {
    while (mongoCursor.hasNext()) {
      String newsArticleContent = mongoCursor.next().getString("content")
          .replaceAll(Regex1, "").replaceAll(Regex2, "").trim();
      newsContent.add(newsArticleContent);
    }
  }
}