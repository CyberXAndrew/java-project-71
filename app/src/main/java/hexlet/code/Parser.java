package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.Objects;

public class Parser {
    public static List<Map<String, Object>> parse(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> result = new ArrayList<>();

        Set<String> checkSet = new HashSet<>(map1.keySet());
        checkSet.addAll(map2.keySet());
        List<String> sortedList = new ArrayList<>(checkSet);
        Collections.sort(sortedList);

        for (String key : sortedList) {
            Map<String, Object> map = new LinkedHashMap<>();
            if (map1.containsKey(key) && map2.containsKey(key) && Objects.equals(map1.get(key), map2.get(key))) {
                map.put("key", key); //пары совпадут целиком
                map.put("oldValue", map1.get(key));
                map.put("status", "no changes");
//                result.append(String.format("    %s: %s\n", key, map1.get(key)));
            } else if (map1.containsKey(key) && map2.containsKey(key) && !Objects.equals(map1.get(key),
                    map2.get(key))) {
                map.put("key", key);  //не совпадут по значению
                map.put("oldValue", map1.get(key));
                map.put("newValue", map2.get(key));
                map.put("status", "changed");
//                result.append(String.format("  - %s: %s\n  + %s: %s\n", key, map1.get(key), key, map2.get(key)));
            } else if (map1.containsKey(key) && !map2.containsKey(key)) { //если был и исчез
                map.put("key", key);
                map.put("oldValue", map1.get(key));
                map.put("status", "deleted");
//                result.append(String.format("  - %s: %s\n", key, map1.get(key)));
            } else if (!map1.containsKey(key) && map2.containsKey(key)) { //не был и был добавлен
                map.put("key", key);
                map.put("newValue", map2.get(key));
                map.put("status", "added");
//                result.append(String.format("  + %s: %s\n", key, map2.get(key)));
            }
            result.add(map);
        }
        return result;
    }

    public static Map<String, Object> getData(String filepath) throws IOException {
        ObjectMapper objectMapper = buildMapper(Differ.getFileFormat(filepath));
        String fileContent = Files.readString(makeAbsolutePath(filepath));
        //Создана строка данных из содержимого файла по абс пути
        return objectMapper.readValue(fileContent, new TypeReference<>() { });
    }

    public static Path makeAbsolutePath(String filepath) {
        Path filePath = Paths.get(filepath); //создан путь из строки String
        return filePath.toAbsolutePath().normalize(); //относительный переведен в абсолютный путь
    }

    public static ObjectMapper buildMapper(String format) throws IOException {
        if (format.equals("json")) {
            return new ObjectMapper();
        } else if (format.equals("yaml")) {
            return new ObjectMapper(new YAMLFactory());
        } else {
            throw new IOException("Wrong file format");
        }
    }
}
