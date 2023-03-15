package problem2;

import java.util.*;

public class P2_AnalysisModel {
  private final int id;
  private final String newsContent;
  private final HashMap<String, Integer> frequencyCount = new HashMap<>();
  private final List<String> wordsMatched = new ArrayList<>();
  private Polarity polarity = Polarity.NEUTRAL_POLARITY;
  private int positiveScore = 0;
  private int negativeScore = 0;
  public enum Polarity {
    POSITIVE_POLARITY,
    NEGATIVE_POLARITY,
    NEUTRAL_POLARITY
  }
  public P2_AnalysisModel(int id, String newsContent) {
    this.id = id;
    this.newsContent = newsContent;
  }
  public int getId() {
    return this.id;
  }
  public String getNewsContent() {
    return this.newsContent;
  }
  public Set<String> getWordsMatched() {
    return (Set<String>) this.wordsMatched;
  }
  public Polarity getPolarity() {
    return this.polarity;
  }
  public void generatePolarity(ArrayList<String> negativeWords,
                               ArrayList<String> positiveWords) {
    Set<Map.Entry<String, Integer>> count = frequencyCount.entrySet();
    String[] contentArray = newsContent.split(" ");
    for (String word : contentArray) {
      String inLowerCase = word.toLowerCase();
      if (frequencyCount.containsKey(inLowerCase)) {
        frequencyCount.put(inLowerCase,
            frequencyCount.get(inLowerCase) + 1);
      } else {
        frequencyCount.put(inLowerCase, 1);
      }
    }
    claculateScore(negativeWords, positiveWords, count);
    findPolarity();
  }

  private void claculateScore(ArrayList<String> negativeWords, ArrayList<String> positiveWords, Set<Map.Entry<String, Integer>> count) {
    for (Map.Entry<String, Integer> map : count) {
      if (negativeWords.contains(map.getKey())) {
        negativeScore = negativeScore + map.getValue();
        wordsMatched.add(map.getKey());
      } else if (positiveWords.contains(map.getKey())) {
        positiveScore = positiveScore + map.getValue();
        wordsMatched.add(map.getKey());
      }
    }
  }

  private void findPolarity() {
    if ((-negativeScore) + (positiveScore) > 0) {
      polarity = Polarity.POSITIVE_POLARITY;
    } else if ((-negativeScore) + (positiveScore) < 0) {
      polarity = Polarity.NEGATIVE_POLARITY;
    } else {
      polarity = Polarity.NEUTRAL_POLARITY;
    }
  }
}