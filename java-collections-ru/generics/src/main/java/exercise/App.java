package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

// BEGIN
public class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> map) {

        List<Map<String, String>> foundBooks = new ArrayList<>();

        for (Map<String, String> book : books) {

            var index = 0;

            for (String key: map.keySet()) {
                if (map.get(key).equals(book.get(key))) {
                    index++;
                }
            }

            if (index == map.size()) {
                foundBooks.add(book);
            }

        }

        return foundBooks;

    }
}
//END
