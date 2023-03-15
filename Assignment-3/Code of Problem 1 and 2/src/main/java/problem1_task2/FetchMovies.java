package problem1_task2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class FetchMovies {
    public String getMovies(String category) {
        try {
            StringBuilder sb = new StringBuilder();
            String newsAPIURL = "http://www.omdbapi.com/?t=" + category.replaceAll(" ", "%20") + "&y=" + 2011 +"&apikey=24465fe3";
            HttpRequest httpRequest = HttpRequest
                    .newBuilder(URI.create(newsAPIURL))
                    .GET()
                    .build();
            HttpResponse<String> httpResponse = HttpClient.newBuilder().build().send(httpRequest, HttpResponse.BodyHandlers.ofString());
            sb.append("{").append("\"movies\"").append(":").append("[").append(httpResponse.body()).append("]").append("}");
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
