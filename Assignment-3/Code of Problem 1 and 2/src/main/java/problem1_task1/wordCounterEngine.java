package problem1_task1;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class wordCounterEngine {
    private String[] CATEGORIES = new String[]{"Canada", "University", "Dalhousie University",
            "Halifax", "Canada Education", "Moncton", "Toronto", "oil", "inflation"};
    private void mapReduce(File[] files, Map<String, Integer> map) {
        for (File newsFile : files) {
            try {
                StringBuilder sb = new StringBuilder();
                Matcher title = Pattern.compile("(\"title\":\".*?\",)").matcher(Files.readString(Paths.get(newsFile.getPath())).trim());
                Matcher content = Pattern.compile("(\"content\":\".*?\"},*)").matcher(Files.readString(Paths.get(newsFile.getPath())).trim());
                while (title.find()) {
                    sb.append(title.group());
                }
                while (content.find()) {
                    sb.append(content.group());
                }
                wordFrequency(map, sb);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void wordFrequency(Map<String, Integer> wordCounterMap, StringBuilder sb) {
        for (String catagory : CATEGORIES) {
            int lastIndex = 0;
            while (lastIndex != -1) {
                lastIndex = sb.toString().indexOf(catagory, lastIndex);
                if (lastIndex != -1) {
                    wordCounterMap.put(catagory, wordCounterMap.get(catagory) + 1);
                    lastIndex = lastIndex + catagory.length();
                }
            }
        }
    }
    public Map<String, Integer> wordCountNewsData() {
        Integer initialCount = 0;
        Map<String, Integer> map = new HashMap<>();
        File[] files = new File("./newsData/").listFiles();
        for (String keyword : CATEGORIES) {
            map.put(keyword, initialCount);
        }
        if (checkMapStatus(map, files)) {
            assert files != null;
            mapReduce(files, map);
        }
        return map;
    }
    private boolean checkMapStatus(Map<String, Integer> map, File[] files) {
        return map.isEmpty() || files != null && files.length != 0;
    }
}