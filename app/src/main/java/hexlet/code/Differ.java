package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Path fileOnePath = Paths.get(filepath1); //создан путь из строки
        Path fileOneAbsPath = fileOnePath.toAbsolutePath().normalize(); //переведен в абсолютный
        String file1 = Files.readString(fileOneAbsPath); //Создана строка из содержимого файла по абс пути
        ObjectMapper objectMapper1 = new ObjectMapper(); //Создан объект класса ObjectMapper
        Map<String, Object> map1 = objectMapper1.readValue(file1, new TypeReference<>() {});
        // карта заполнена значениями из файла
        Path fileTwoPath = Paths.get(filepath2);
        Path fileTwoAbsPath = fileTwoPath.toAbsolutePath().normalize();
        String file2 = Files.readString(fileTwoAbsPath);
        ObjectMapper objectMapper2 = new ObjectMapper();
        Map<String, Object> map2 = objectMapper2.readValue(file2, new TypeReference<>() {});

        Set<String> checkSet = new HashSet<>(map1.keySet());
        checkSet.addAll(map2.keySet());
        List<String> sortedList = new ArrayList<>(checkSet);
        Collections.sort(sortedList);

        String result = "";
        for (var key : sortedList) {
            if (map1.get(key).equals(map2.get(key))) { //пары совпадут целиком
                result += "    " + key + ": " + map1.get(key) + "\n";
            } else if (!map1.get(key).equals(map2.get(key))) { //не совпадут по значению
                result += "    -" + key + ": " + map1.get(key) + "\n" + "    +" + key + ": " + map2.get(key) + "\n";
            } else if (map1.containsKey(key) && !map2.containsKey(key)) { //если был и исчез
                result += "    +" + key + ": " + map1.get(key) + "\n";
            } else if (!map1.containsKey(key) && map2.containsKey(key)) { //не был и был добавлен
                result += "    +" + key + ": " + map1.get(key) + "\n";
            }
        }
        return result;
    }
}
// Stream.of(file1.split("\n"))
//                .filter(str -> str.trim().startsWith("\""))
//                .map(str -> str.replaceAll(",", ""))
//                .sorted()
//                .forEach(str -> file1map.put(str.split(": ")[0], str.split(": ")[1]));