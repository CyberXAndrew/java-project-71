package hexlet.code;

import hexlet.code.formatters.Stylish;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String chooseFormat(List<Map<String, Object>> data, String format) {
        switch (format) {
            case "stylish" -> Stylish.makeStylish(data);
            default -> Stylish.makeStylish(data); //throw new IllegalArgumentException("Unknown output format");
        }
        return Stylish.makeStylish(data);
    }
}
