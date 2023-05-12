package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception { // String format
        Path fileOnePath = Paths.get(filepath1); //создан путь из строки String
        Path fileOneAbsPath = fileOnePath.toAbsolutePath().normalize(); //относительный переведен в абсолютный путь
        String file1 = Files.readString(fileOneAbsPath); //Создана строка данных из содержимого файла по абс пути
        ObjectMapper objectMapper1 = new ObjectMapper(); //Создан объект класса ObjectMapper
        Map<String, Object> map1 = objectMapper1.readValue(file1, new TypeReference<>(){});
        // карта заполнена значениями из файла
        Path fileTwoPath = Paths.get(filepath2);
        Path fileTwoAbsPath = fileTwoPath.toAbsolutePath().normalize();
        String file2 = Files.readString(fileTwoAbsPath);
        ObjectMapper objectMapper2 = new ObjectMapper();
        Map<String, Object> map2 = objectMapper2.readValue(file2, new TypeReference<>(){});

        Set<String> checkSet = new HashSet<>(map1.keySet());
        checkSet.addAll(map2.keySet());
        List<String> sortedList = new ArrayList<>(checkSet);
        Collections.sort(sortedList);

        String result = "{\n";
        for (String key : sortedList) {
            if (map1.containsKey(key) && map2.containsKey(key) && Objects.equals(map1.get(key), map2.get(key))) {//map1.get(key).equals(map2.get(key))) { //пары совпадут целиком
                result += String.format("  %s: %s\n", key, map1.get(key));
            } else if (map1.containsKey(key) && map2.containsKey(key) && !map1.get(key).equals(map2.get(key))) { //не совпадут по значению
                result += String.format("  -%s: %s\n  +%s: %s\n", key, map1.get(key), key, map2.get(key));
            } else if (map1.containsKey(key) && !map2.containsKey(key)) { //если был и исчез
                result += String.format("  -%s: %s\n", key, map1.get(key));
            } else if (!map1.containsKey(key) && map2.containsKey(key)) { //не был и был добавлен
                result += String.format("  +%s: %s\n", key, map2.get(key));
            }
        }
        return result + "}";
    }
}
