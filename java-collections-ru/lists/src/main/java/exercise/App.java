package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {

    public static String[] getArr(String word) {

        char[] chars = word.toCharArray();

        String[] strings = new String[chars.length];

        for (var i = 0; i < chars.length; i++) {
            strings[i] = String.valueOf(chars[i]);
        }

        return strings;
    }

    public static boolean scrabble(String symbolsSet, String word) {

        String[] symbolsTemp = getArr(symbolsSet);
        List<String> symbols = new ArrayList<>(Arrays.asList(symbolsTemp));

        var wordLowerCase = word.toLowerCase();
        String[] wordSymbolsTemp = getArr(wordLowerCase);
        List<String> wordSymbols = new ArrayList<>(Arrays.asList(wordSymbolsTemp));

        for (String symbol: wordSymbols) {
            if (symbols.contains(symbol)) {
                symbols.remove(symbol);
            } else {
                return false;
            }
        }

        return true;

    }
}
//END
