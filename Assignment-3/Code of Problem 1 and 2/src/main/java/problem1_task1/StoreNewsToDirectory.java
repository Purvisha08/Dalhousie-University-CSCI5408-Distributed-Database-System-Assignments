package problem1_task1;

import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class StoreNewsToDirectory {
    public void storingNews(String category, String newsInJSONFormat) {
        String fileCategory = category.replaceAll(" ", "").toLowerCase();
        try {
            if (checkFileStatus()) {
                Files.createDirectory(Paths.get("./newsData/"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        createAndWriteData(newsInJSONFormat, fileCategory);
    }

    private boolean checkFileStatus() {
        return !Files.exists(Paths.get("./newsData/"));
    }

    private void createAndWriteData(String newsInJSONFormat, String fileCategory) {
        String newsDataFileName = "./newsData/" + fileCategory + "_" + System.currentTimeMillis() + ".json";
        try (FileWriter fileWriter = new FileWriter(newsDataFileName, StandardCharsets.UTF_8)) {
            fileWriter.write(newsInJSONFormat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
