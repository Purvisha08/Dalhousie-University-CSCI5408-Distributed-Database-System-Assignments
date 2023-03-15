package problem1_task1;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class FetchNews {
    public String getNews(String category) {
        try {
            String newsAPIURL = "https://newsapi.org/v2/everything?q=" + category.replaceAll(" ", "%20") + "&language=en&pageSize=" + 100;
            HttpRequest httpRequest = HttpRequest
                    .newBuilder(URI.create(newsAPIURL))
                    .header("X-Api-Key", "ec5df9943ae74aaea2f39fb91189af73")
                    .timeout(Duration.of(1, ChronoUnit.MINUTES))
                    .GET()
                    .build();
            HttpResponse<String> httpResponse = HttpClient.newBuilder().build().send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return httpResponse.body();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
