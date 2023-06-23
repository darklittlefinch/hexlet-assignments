package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// BEGIN
class App {
    public static LinkedHashMap genDiff(Map<String, Object> map1, Map<String, Object> map2) {

        Set<String> set1 = new TreeSet<>(map1.keySet());
        Set<String> set2 = new TreeSet<>(map2.keySet());

        Set<String> added = new TreeSet<>(map2.keySet());
        added.removeAll(set1);

        Set<String> deleted = new TreeSet<>(map1.keySet());
        deleted.removeAll(set2);

        Set<String> intersection = new TreeSet<>();
        intersection.addAll(set1);
        intersection.retainAll(set2);

        Set<String> changed = new TreeSet<>();
        Set<String> unchanged = new TreeSet<>();

        for (String item: intersection) {
            var value1 = map1.get(item);
            var value2 = map2.get(item);

            if (value1.equals(value2)) {
                unchanged.add(item);
            } else {
                changed.add(item);
            }

        }

        LinkedHashMap<String, String> result = new LinkedHashMap<>();

        for (String item: added) {
            result.put(item, "added");
        }

        for (String item: deleted) {
            result.put(item, "deleted");
        }

        for (String item: changed) {
            result.put(item, "changed");
        }

        for (String item: unchanged) {
            result.put(item, "unchanged");
        }

        return result;
    }
}
//END
