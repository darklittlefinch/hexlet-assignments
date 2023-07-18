package exercise;

import java.util.Map;
import java.util.List;

// BEGIN
public class PairedTag extends Tag {

    private final String body;
    private final List<Tag> children;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> children) {
        super(name, attributes);
        this.body = body;
        this.children = children;
    }

    public String toString() {

        Tag single = new SingleTag(getName(), getAttributes());

        var result = new StringBuilder(single.toString());
        result.append(body);

        for (Tag singleTag : children) {
            result.append(singleTag.toString());
        }

        result.append("</");
        result.append(getName());
        result.append(">");

        return result.toString();
    }
}
// END
