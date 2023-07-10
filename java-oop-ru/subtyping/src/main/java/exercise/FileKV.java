package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {

    private final String filepath;
    private final Map<String, String> beginStorage;

    public FileKV(String filepath, Map<String, String> beginStorage) {
        this.filepath = filepath;
        this.beginStorage = new HashMap<>(beginStorage);
        var content = Utils.serialize(beginStorage);
        Utils.writeFile(filepath, content);
    }

    @Override
    public void set(String key, String value) {
        var content = Utils.readFile(filepath);
        Map<String, String> storage = Utils.unserialize(content);
        storage.put(key, value);
        var newContent = Utils.serialize(storage);
        Utils.writeFile(filepath, newContent);
    }

    @Override
    public void unset(String key) {
        var content = Utils.readFile(filepath);
        Map<String, String> storage = Utils.unserialize(content);
        storage.remove(key);
        var newContent = Utils.serialize(storage);
        Utils.writeFile(filepath, newContent);
    }

    @Override
    public String get(String key, String defaultValue) {
        var content = Utils.readFile(filepath);
        Map<String, String> storage = Utils.unserialize(content);
        return storage.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        var content = Utils.readFile(filepath);
        Map<String, String> storage = Utils.unserialize(content);
        return new HashMap<>(storage);
    }
}
// END
