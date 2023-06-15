package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        String[] words = sentence.split(" ");
        Map<String, Integer> wordsOfSentence = new HashMap<>();

        for (String word: words) {
            if (!wordsOfSentence.containsKey(word)) {
                wordsOfSentence.putIfAbsent(word, 1);
            } else {
                var newValue = wordsOfSentence.get(word) + 1;
                wordsOfSentence.put(word, newValue);
            }
        }

        return wordsOfSentence;
    }

    public static String toString(Map<String, Integer> wordsOfSentence) {
        var result = new StringBuilder();

        result.append("=>");
        result.append("{");

        for (String key: wordsOfSentence.keySet()) {
            result.append(key + ": " + wordsOfSentence.get(key));
        }
        result.append("}");

        return result.toString();
    }
}
//END
