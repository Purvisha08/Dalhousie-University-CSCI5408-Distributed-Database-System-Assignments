package problem1_task2;

import org.json.JSONObject;

public class MovieDbExtraction {
    FetchMovies fetchMovies = new FetchMovies();
    StoreMoviesToDirectory storeMoviesToDirectory = new StoreMoviesToDirectory();
    private final String[] CATEGORIES = new String[]{"Canada", "University",
            "Halifax", "Vancouver", "Moncton", "Toronto", "Alberta", "Niagara"};

    private final String openeingBracket = "[";
    private final String closingBracket = "]";
    public void extractMovies() {
        for (String category : CATEGORIES) {
            System.out.println("Catagory: "+category+" - Fetching and Storing News");
            String moviesInJSONFormat = fetchMovies.getMovies(category);
            if (moviesInJSONFormat != null) {
                StringBuilder sb = new StringBuilder();
                int numberOfMoviesInEachFile = 0;
                numberOfMoviesInEachFile = getNumberOfArticlesInEachFile(category, moviesInJSONFormat, sb, numberOfMoviesInEachFile);
                storeExtraArticle(category, sb, numberOfMoviesInEachFile);
            }
        }
    }

    private int getNumberOfArticlesInEachFile(String category, String newsInJSONFormat, StringBuilder sb, int numberOfArticlesInEachFile) {
        for (int i = 0; i < new JSONObject(newsInJSONFormat).getJSONArray("movies").length(); ++i) {
            sb.append(new JSONObject(newsInJSONFormat).getJSONArray("movies").getJSONObject(i)).append(",");
            numberOfArticlesInEachFile++;
            if (numberOfArticlesInEachFile == 5) {
                sb.setLength(sb.length() - 1);
                String numberOfArticlesInEachFileSB = openeingBracket + sb + closingBracket;
                storeMoviesToDirectory.storingMovies(category, numberOfArticlesInEachFileSB);
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
            storeMoviesToDirectory.storingMovies(category, numberOfArticlesInEachFileSB);
            sb.setLength(0);
        }
    }

}
