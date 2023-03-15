package problem1_task1;

import java.util.Map;

public class MapReduce {
    public void mapReduce (){
        wordCounterEngine wordCounterEngine = new wordCounterEngine();

        int higestCount = -1;
        int lowestCount = -1;
        String wordWithHigestFrequency = null;
        String wordWithLowestFrequency = null;

        System.out.println("\nMap Reduce");
        System.out.println("-------------------------------------------------------");

        System.out.printf("%n%-20s%-15s%n", "Word", "Frequency");
        createTable(wordCounterEngine);
        for (Map.Entry<String, Integer> frequency : wordCounterEngine.wordCountNewsData().entrySet()) {
            if (checkCount(higestCount, lowestCount)) {
                wordWithLowestFrequency = frequency.getKey();
                wordWithHigestFrequency = frequency.getKey();
                lowestCount = frequency.getValue();
                higestCount = frequency.getValue();
            } else {
                if (checkLowestCount(lowestCount, frequency)) {
                    wordWithLowestFrequency = frequency.getKey();
                    lowestCount = frequency.getValue();
                }
                if (checkHighestCount(higestCount, frequency)) {
                    wordWithHigestFrequency = frequency.getKey();
                    higestCount = frequency.getValue();
                }
            }
        }
        System.out.println("\nWord with higest frequency is \"" + wordWithHigestFrequency + "\", which is \"" + higestCount + "\".");
        System.out.println("Word with lowest frequency is \"" + wordWithLowestFrequency + "\", which is \"" + lowestCount + "\".");
    }

    private void createTable(wordCounterEngine wordCounterEngine) {
        for (Map.Entry<String, Integer> frequency : wordCounterEngine.wordCountNewsData().entrySet()) {
            System.out.printf("%n%-20s%-15s%n", frequency.getKey(), frequency.getValue());
        }
    }

    private boolean checkHighestCount(int higestCount, Map.Entry<String, Integer> frequency) {
        return frequency.getValue() > higestCount;
    }

    private boolean checkLowestCount(int lowestCount, Map.Entry<String, Integer> frequency) {
        return frequency.getValue() < lowestCount;
    }

    private boolean checkCount(int higestCount, int lowestCount) {
        return lowestCount == -1 && higestCount == -1;
    }
}


