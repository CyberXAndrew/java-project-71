package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String fileContent1 = getContent(filepath1);
        String fileFormat1 = getFileFormat(filepath1);
        Map<String, Object> map1 = Parser.parse(fileContent1, fileFormat1);

        String fileContent2 = getContent(filepath2);
        String fileFormat2 = getFileFormat(filepath2);
        Map<String, Object> map2 = Parser.parse(fileContent2, fileFormat2);

        List<Map<String, Object>> data = DiffBuilder.buildDifference(map1, map2);
        return Formatter.applyDisplayFormat(data, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String getContent(String filepath) throws IOException {
        return Files.readString(makeAbsolutePath(filepath));
    }
    public static String getFileFormat(String filepath) {
        String absPath = makeAbsolutePath(filepath).toString();
        return absPath.substring(absPath.indexOf(".") + 1);
    }
    public static Path makeAbsolutePath(String filepath) {
        Path filePath = Paths.get(filepath);
        return filePath.toAbsolutePath().normalize();
    }
}
