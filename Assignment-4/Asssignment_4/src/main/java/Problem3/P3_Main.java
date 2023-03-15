package Problem3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class P3_Main {
  public static void main(String[] args) {
    run();
    System.out.println("Semantic analysis of news Article in progress...");
    P3_AnalysisModel analysisModel = new P3_SemanticAnalysis().perfromAnalysis();
    try (FileWriter fw = new FileWriter("src/main/java/Problem3/P3_output.txt", true)) {
      getfrequency(-1, -1);
      System.out.println("Output in file: P3_output.txt");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
  private static void run() {
    System.setProperty("java.net.preferIPv4Stack", "true");
    Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
  }
  private static void getfrequency(int highestWordIndex, float highestWordFreq) {
    for (int i = 0; i < new P3_SemanticAnalysis().perfromAnalysis().getWordFrequencyList().size(); ++i) {
      P3_AnalysisModel.wordFrequency frequency = new P3_SemanticAnalysis().perfromAnalysis().getWordFrequencyList().get(i);
      if (highestWordIndex == -1) {
        highestWordIndex = i; highestWordFreq = ((float) frequency.getFrequency() / frequency.getTotalCount());
      } else {
        float currentFrequency = ((float) frequency.getFrequency() / frequency.getTotalCount());
        if (currentFrequency > highestWordFreq) {
          highestWordIndex = i; highestWordFreq = currentFrequency;
        }
      }
    }
  }
}