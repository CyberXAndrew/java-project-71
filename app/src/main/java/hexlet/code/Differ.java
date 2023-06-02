package hexlet.code;

import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> map1 = Parser.getData(filepath1);
        Map<String, Object> map2 = Parser.getData(filepath2);
        if (!checkFileFormats(filepath1, filepath2)) {
            throw new Exception("Different file formats!");
        }
        List<Map<String, Object>> data = Parser.parse(map1, map2); //вернет List<str obj>
        return Formatter.chooseFormat(data, format);
    }

    public static String getFileFormat(String filepath) {
        String absPath = Parser.makeAbsolutePath(filepath).toString();
        return absPath.substring(absPath.indexOf(".") + 1);
    }

    public static boolean checkFileFormats(String filepath1, String filepath2) {
        return getFileFormat(filepath1).equals(getFileFormat(filepath2));
    }
}
