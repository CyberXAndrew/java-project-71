package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Objects;

public class Parser {
    public static String parse(Map<String, Object> map1, Map<String, Object> map2) {
        StringBuilder result = new StringBuilder("{\n");

        Set<String> checkSet = new HashSet<>(map1.keySet());
        checkSet.addAll(map2.keySet());
        List<String> sortedList = new ArrayList<>(checkSet);
        Collections.sort(sortedList);

        for (String key : sortedList) {
            if (map1.containsKey(key) && map2.containsKey(key) && Objects.equals(map1.get(key), map2.get(key))) {//пары совпадут целиком
                result.append(String.format("    %s: %s\n", key, map1.get(key)));
            } else if (map1.containsKey(key) && map2.containsKey(key) && !map1.get(key).equals(map2.get(key))) { //не совпадут по значению
                result.append(String.format("  - %s: %s\n  + %s: %s\n", key, map1.get(key), key, map2.get(key)));
            } else if (map1.containsKey(key) && !map2.containsKey(key)) { //если был и исчез
                result.append(String.format("  - %s: %s\n", key, map1.get(key)));
            } else if (!map1.containsKey(key) && map2.containsKey(key)) { //не был и был добавлен
                result.append(String.format("  + %s: %s\n", key, map2.get(key)));
            }
        }
        return result + "}";
    }

    public static Map<String, Object> getData(String filepath, String format) throws IOException {
        ObjectMapper objectMapper = buildMapper(format);
        String fileContent = Files.readString(makeAbsolutePath(filepath)); //Создана строка данных из содержимого файла по абс пути
        return objectMapper.readValue(fileContent, new TypeReference<>(){});
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
