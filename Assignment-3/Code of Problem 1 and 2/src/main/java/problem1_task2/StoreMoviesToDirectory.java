package problem1_task2;

import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StoreMoviesToDirectory {
    public void storingMovies(String category, String moviesInJSONFormat) {
        String fileCategory = category.replaceAll(" ", "").toLowerCase();
        try {
            if (checkFileStatus()) {
                Files.createDirectory(Paths.get("./moviesData/"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        createAndWriteData(moviesInJSONFormat, fileCategory);
    }

    private boolean checkFileStatus() {
        return !Files.exists(Paths.get("./moviesData/"));
    }

    private void createAndWriteData(String moviesInJSONFormat, String fileCategory) {
        String moviesDataFileName = "./moviesData/" + fileCategory + "_" + System.currentTimeMillis() + ".json";
        try (FileWriter fileWriter = new FileWriter(moviesDataFileName, StandardCharsets.UTF_8)) {
            fileWriter.write(moviesInJSONFormat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
