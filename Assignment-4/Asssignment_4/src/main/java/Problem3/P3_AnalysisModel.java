package Problem3;
import java.util.List;
public class P3_AnalysisModel {
  public static class wordFrequency {
    private final int totalCount;
    private final int frequency;
    private final String newsArticlesName;
    public wordFrequency(String articleName,
                         String articleContent,
                         int totalWordsCount,
                         int frequency) {
      this.newsArticlesName = articleName;
      this.totalCount = totalWordsCount;
      this.frequency = frequency;
    }
    public String getNewsArticlesName() {
      return this.newsArticlesName;
    }
    public int getTotalCount() {
      return this.totalCount;
    }
    public int getFrequency() {
      return this.frequency;
    }
  }
  public static class tfidf {
    public tfidf(String searchQuery, int documentContainingTerm, double totalDocWithTerm, double log10NTotalDocWithTerm) {
    }
  }
  private final int totalDoc;
  private final List<tfidf> tfidfList;
  private final String term;
  private final List<wordFrequency> wordFrequencyList;
  public P3_AnalysisModel(int totalDocuments,
                          List<tfidf> tfidfList,
                          String term,
                          List<wordFrequency> termWordFreqList) {
    this.totalDoc = totalDocuments;
    this.tfidfList = tfidfList;
    this.term = term;
    this.wordFrequencyList = termWordFreqList;
  }
  public List<wordFrequency> getWordFrequencyList() {
    return this.wordFrequencyList;
  }
}