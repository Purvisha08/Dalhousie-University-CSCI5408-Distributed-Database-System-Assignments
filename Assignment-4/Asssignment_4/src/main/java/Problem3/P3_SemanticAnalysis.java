package Problem3;

import com.mongodb.client.*;
import org.bson.Document;
import java.util.*;
public class P3_SemanticAnalysis {
  String Regex1 = "\\[\\d+ chars]";
  String Regex2 = "(\")|(\\{)|(})|(…)";
  ArrayList<P3_AnalysisModel.tfidf> list = new ArrayList<>();
  public P3_AnalysisModel perfromAnalysis() {
    Set<String> newsContant = new HashSet<>();
    try (MongoClient mongoClient = MongoClients.create("mongodb+srv://purvisha99:purvisha99@mymongonews.eqq5b.mongodb.net/mongoNews?retryWrites=true&w=majority")) {
      MongoCursor<Document> mongoCursor = mongoClient.getDatabase("mongoNews").getCollection("myMongoNews").find().iterator();
      getNewsContent(mongoCursor, newsContant);
      if (newsContant.size() > 0) {
        Map<String, Integer> map = new HashMap<>();
        String firstWord = "people";
        List<P3_AnalysisModel.wordFrequency> wordFreq = new ArrayList<>();
        for (String newsContent : newsContant) {
          for (String query : new String[]{"weather", "people", "condition"}) {
            get(map, newsContent, query);
          }
        }
        for (Map.Entry<String, Integer> query : map.entrySet()) {
          list.add(new P3_AnalysisModel.tfidf(
              query.getKey(),
              query.getValue(),
                  ((double) newsContant.size() / query.getValue()),
                  Math.log10(((double) newsContant.size() / query.getValue()))
          ));
        }
        return new P3_AnalysisModel( newsContant.size(), list, firstWord, wordFreq);
      }
      return null;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private void get(Map<String, Integer> map, String newsContent, String query) {
    if (newsContent.contains(query)) {
      if (map.containsKey(query)) {
        map.put(query, map.get(query) + 1);
      } else {
        map.put(query, 1);
      }
    }
  }

  private void getNewsContent(MongoCursor<Document> mongoCursor, Set<String> newsArticleContent) {
    while (mongoCursor.hasNext()) {
      String content = mongoCursor.next().getString("content")
          .replaceAll(Regex1, "")
          .replaceAll(Regex2, "")
          .trim();
      newsArticleContent.add(content);
    }
  }
}