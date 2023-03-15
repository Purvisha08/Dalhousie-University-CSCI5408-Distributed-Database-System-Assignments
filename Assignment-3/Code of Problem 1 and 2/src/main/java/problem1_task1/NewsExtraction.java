package problem1_task1;

import org.json.JSONObject;

public final class NewsExtraction {
    FetchNews fetchNews = new FetchNews();
    StoreNewsToDirectory storeNewsToDirectory = new StoreNewsToDirectory();
    private final String[] CATEGORIES = new String[]{"Canada", "University", "Dalhousie University",
            "Halifax", "Canada Education", "Moncton", "Toronto", "oil", "inflation"};
    private final String openeingBracket = "[";
    private final String closingBracket = "]";
    public void extractNews() {
        for (String category : CATEGORIES) {
            System.out.println("Catagory: "+category+" - Fetching and Storing News");
            String newsInJSONFormat = fetchNews.getNews(category);
            if (newsInJSONFormat != null) {
                StringBuilder sb = new StringBuilder();
                int numberOfArticlesInEachFile = 0;
                numberOfArticlesInEachFile = getNumberOfArticlesInEachFile(category, newsInJSONFormat, sb, numberOfArticlesInEachFile);
                storeExtraArticle(category, sb, numberOfArticlesInEachFile);
            }
        }
    }

    private int getNumberOfArticlesInEachFile(String category, String newsInJSONFormat, StringBuilder sb, int numberOfArticlesInEachFile) {
        for (int i = 0; i < new JSONObject(newsInJSONFormat).getJSONArray("articles").length(); ++i) {
            sb.append(new JSONObject(newsInJSONFormat).getJSONArray("articles").getJSONObject(i)).append(",");
            numberOfArticlesInEachFile++;
            if (numberOfArticlesInEachFile == 5) {
                sb.setLength(sb.length() - 1);
                String numberOfArticlesInEachFileSB = openeingBracket + sb + closingBracket;
                storeNewsToDirectory.storingNews(category, numberOfArticlesInEachFileSB);
                numberOfArticlesInEachFile = 0;
                sb.setLength(0);
            }
        }
        return numberOfArticlesInEachFile;
    }

    private void storeExtraArticle(String category, StringBuilder sb, int numberOfArticlesInEachFile) {
        if (numberOfArticlesInEachFile > 0) {
            sb.setLength(sb.length() - 1);
            String numberOfArticlesInEachFileSB = openeingBracket + sb + closingBracket;
            storeNewsToDirectory.storingNews(category, numberOfArticlesInEachFileSB);
            sb.setLength(0);
        }
    }
}