package hexlet.code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.Objects;

public class DiffBuilder {
    public static List<Map<String, Object>> buildDifference(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> result = new ArrayList<>();

        Set<String> checkSet = new HashSet<>(map1.keySet());
        checkSet.addAll(map2.keySet());
        List<String> sortedList = new ArrayList<>(checkSet);
        Collections.sort(sortedList);

        for (String key : sortedList) {
            Map<String, Object> map = new LinkedHashMap<>();
            if (map1.containsKey(key) && map2.containsKey(key) && Objects.equals(map1.get(key), map2.get(key))) {
                map.put("key", key);
                map.put("oldValue", map1.get(key));
                map.put("status", "no changes");
            } else if (map1.containsKey(key) && map2.containsKey(key) && !Objects.equals(map1.get(key),
                    map2.get(key))) {
                map.put("key", key);
                map.put("oldValue", map1.get(key));
                map.put("newValue", map2.get(key));
                map.put("status", "changed");
            } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                map.put("key", key);
                map.put("oldValue", map1.get(key));
                map.put("status", "deleted");
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                map.put("key", key);
                map.put("newValue", map2.get(key));
                map.put("status", "added");
            }
            result.add(map);
        }
        return result;
    }
}
