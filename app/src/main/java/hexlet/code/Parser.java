/*package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Parser {
    public String parse(Map<String, Object> map1, Map<String, Object> map2, List<String> sortedList, String format) {
        String result = "";
        String end = "";
        switch (format) {
            case ("json"):
                result = "{\n";
                end = "}";
                break;
            case ("yaml")
        }

        for (String key : sortedList) {
            if (map1.containsKey(key) && map2.containsKey(key) && Objects.equals(map1.get(key), map2.get(key))) {//map1.get(key).equals(map2.get(key))) { //пары совпадут целиком
                result += String.format("    %s: %s\n", key, map1.get(key));
            } else if (map1.containsKey(key) && map2.containsKey(key) && !map1.get(key).equals(map2.get(key))) { //не совпадут по значению
                result += String.format("  - %s: %s\n  + %s: %s\n", key, map1.get(key), key, map2.get(key));
            } else if (map1.containsKey(key) && !map2.containsKey(key)) { //если был и исчез
                result += String.format("  - %s: %s\n", key, map1.get(key));
            } else if (!map1.containsKey(key) && map2.containsKey(key)) { //не был и был добавлен
                result += String.format("  + %s: %s\n", key, map2.get(key));
            }
        }
        return result + end;
    }
}*/
