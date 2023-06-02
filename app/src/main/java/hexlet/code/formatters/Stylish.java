package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String makeStylish(List<Map<String, Object>> data) {
        StringBuilder sb = new StringBuilder("{\n");
        for (Map<String, Object> map : data) {
            if (map.get("status").equals("no changes")) {
                sb.append(String.format("    %s: %s\n", map.get("key"), map.get("oldValue")));
            } else if (map.get("status").equals("changed")) {
                sb.append(String.format("  - %s: %s\n  + %s: %s\n", map.get("key"), map.get("oldValue"), map.get("key"),
                        map.get("newValue")));
            } else if (map.get("status").equals("deleted")) {
                sb.append(String.format("  - %s: %s\n", map.get("key"), map.get("oldValue")));
            } else if (map.get("status").equals("added")) {
                sb.append(String.format("  + %s: %s\n", map.get("key"), map.get("newValue")));
            }
        }
        return sb + "}";
    }
}
