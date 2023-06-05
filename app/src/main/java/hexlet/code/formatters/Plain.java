package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String makePlain(List<Map<String, Object>> data) {
        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> map : data) {
            if (sb.length() != 0 && !map.get("status").equals("no changes")) {
                sb.append("\n");
            }
            if (map.get("status").equals("changed")) {
                sb.append(String.format("Property '%s' was updated. From %s to %s", map.get("key"),
                        correctSyntax(map.get("oldValue")), correctSyntax(map.get("newValue"))));
            } else if (map.get("status").equals("deleted")) {
                sb.append(String.format("Property '%s' was removed", map.get("key")));
            } else if (map.get("status").equals("added")) {
                sb.append(String.format("Property '%s' was added with value: %s", map.get("key"),
                        correctSyntax(map.get("newValue"))));
            }
        }
        return sb.toString();
    }
    public static Object correctSyntax(Object o) {
        if (o instanceof String) {
            return "'" + o + "'";
        } else if (o instanceof Map || o instanceof List) {
            return "[complex value]";
        }
        return o;
    }
}
