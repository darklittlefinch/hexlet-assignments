package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String sentence) {

        Map<String, Integer> wordsOfSentence = new HashMap<>();
        if (sentence.equals("")) {
            return wordsOfSentence;
        }

        String[] words = sentence.split(" ");

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

        if (wordsOfSentence.isEmpty()) {
            return "{}";
        }

        var result = new StringBuilder();

        result.append("{\n");

        for (String key: wordsOfSentence.keySet()) {
            result.append("  " + key + ": " + wordsOfSentence.get(key) + "\n");
        }

        result.append("}");

        return result.toString();
    }
}

//END
