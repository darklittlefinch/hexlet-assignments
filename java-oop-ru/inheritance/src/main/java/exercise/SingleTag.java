package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {

    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }

    public String toString() {

        var result = new StringBuilder();

        result.append("<");
        result.append(getName());

        var attributes = getAttributes();

        for (String key: attributes.keySet()) {
            result.append(" ");
            result.append(key);
            result.append("=");

            result.append("\"");
            result.append(attributes.get(key));
            result.append("\"");
        }

        result.append(">");
        return result.toString();
    }
}
// END
