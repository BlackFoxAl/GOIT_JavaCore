package Module10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class OccurrenceFrequencyWordsTest {
    public static void main(String[] args) {
        String filename = "word.txt";
        String dataLine;
        String delimeter = "\\s+";
        HashMap <String , Integer> wordsHashMap = new HashMap <>();
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
        wordsHashMap.forEach((key,value) -> {
            System.out.println(key + " " + value);
        });
    }
}
