package problem2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class P2_Main {
  public static void main(String[] args) {
    run();
    String outputFile = "src/main/java/problem2/problem_2_output.txt";
    System.out.println("Sentiment analysis of News Article from MongoDB is in progress...");
    List<P2_AnalysisModel> analysisModelList = new P2_SentimentAnalysis().performAnalysis();
    try (FileWriter fw = new FileWriter(outputFile, true)) {
      generateReport(analysisModelList, fw);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  private static void run() {
    System.setProperty("java.net.preferIPv4Stack", "true");
    Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
  }

  private static void generateReport(List<P2_AnalysisModel> analysisModelList, FileWriter fw) throws IOException {
    for (P2_AnalysisModel news : analysisModelList) {
      fw.append("News Article Id: ").append(String.valueOf(news.getId()))
              .append("\n News Description: ").append(news.getNewsContent())
              .append("\n Match: ").append(String.valueOf(news.getWordsMatched()))
              .append("\n Polarity: ").append(String.valueOf(news.getPolarity())).append("\n\n");
    }
  }
}