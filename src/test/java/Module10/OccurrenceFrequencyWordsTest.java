package Module10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class OccurrenceFrequencyWordsTest {
    public static void main(String[] args) {
        String filename = "word.txt";
        String dataLine;
        String delimeter = "\\s+";
        Map <String , Integer> wordsHashMap = new HashMap <>();
        StringBuffer outputJson = new StringBuffer();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            while ((dataLine = bufferedReader.readLine()) != null) {
                for (String word : dataLine.split(delimeter)) {
                    if (wordsHashMap.containsKey(word)) {
                        wordsHashMap.replace(word,wordsHashMap.get(word) + 1);
                        continue;
                    }
                    wordsHashMap.put(word,1);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        };

        wordsHashMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(System.out::println);
    }
}
