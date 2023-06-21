package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> people) {
        return people.stream()
                .filter(man -> man.get("gender").equals("male"))
                .sorted((man1, man2) -> man1.get("birthday").compareTo(man2.get("birthday")))
                .map(man -> man.get("name"))
                .collect(Collectors.toList());
    }
}
// END
