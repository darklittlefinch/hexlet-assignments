package exercise;

import java.util.Map;

// BEGIN
public class App {
    public static KeyValueStorage swapKeyValue(KeyValueStorage storage) {

        for (Map.Entry<String, String> entry: storage.toMap().entrySet()) {
            var newKey = entry.getValue();
            var newValue = entry.getKey();

            storage.unset(newValue);
            storage.set(newKey, newValue);
        }

        return storage;
    }

    public static void main(String[] args) {
        KeyValueStorage storage1 = new InMemoryKV(Map.of("key", "value"));
        System.out.println(storage1.toMap());

        storage1.set("key2", "value2");
        System.out.println(storage1.toMap());

        swapKeyValue(storage1);
        System.out.println(storage1.toMap() + "\n");

        KeyValueStorage storage2 = new InMemoryKV(Map.of("foo", "bar", "bar", "zoo"));
        System.out.println(storage2.toMap());

        swapKeyValue(storage2);
        System.out.println(storage2.toMap());
    }
}
// END
