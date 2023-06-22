import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JavaWordCount {

    public static void main(String[] args) {
        String filename = "C:\\Users\\HP\\Desktop\\For SBD\\sepuluhgb.txt"; // Replace with the actual file path

        long totalTime = 0;
        int numRuns = 1;

        for (int i = 0; i < numRuns; i++) {
            long startTime = System.currentTimeMillis();

            Map<String, Integer> wordCountMap = new HashMap<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] words = line.trim().split("\\s+");
                    for (String word : words) {
                        wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            long endTime = System.currentTimeMillis();
            long runtime = endTime - startTime;
            totalTime += runtime;

            //System.out.println("Run " + (i + 1) + " - Word count: " + wordCountMap.size());
            System.out.println("Run " + (i + 1) + " - Runtime: " + runtime + " milliseconds\n");

            String outputFilename = "output_run_" + (i + 1) + ".txt";
            saveWordCountToFile(wordCountMap, outputFilename);
        }

        long averageRuntime = totalTime / numRuns;

        System.out.println("Average Runtime: " + averageRuntime + " milliseconds");
    }

    private static void saveWordCountToFile(Map<String, Integer> wordCountMap, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
