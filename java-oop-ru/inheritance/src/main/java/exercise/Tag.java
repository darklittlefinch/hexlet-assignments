package exercise;

import java.util.Map;

// BEGIN
public abstract class Tag {

    private final String name;
    private final Map<String, String> attributes;

    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public final String getName() {
        return name;
    }

    public final Map<String, String> getAttributes() {
        return attributes;
    }

    public abstract String toString();
}
// END
