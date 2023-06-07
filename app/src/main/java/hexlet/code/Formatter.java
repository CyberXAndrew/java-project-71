package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String applyDisplayFormat(List<Map<String, Object>> data, String format) {
        return switch (format) {
            case "stylish" -> Stylish.makeStylish(data);
            case "plain" -> Plain.makePlain(data);
            case "json" -> Json.makeJson(data);
            default -> throw new IllegalArgumentException("Unknown output format: " + format);
        };
    }
}
