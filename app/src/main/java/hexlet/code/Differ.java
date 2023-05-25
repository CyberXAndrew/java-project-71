package hexlet.code;

import java.io.IOException;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> map1 = Parser.getData(filepath1, format);
        Map<String, Object> map2 = Parser.getData(filepath2, format);
        return Parser.parse(map1, map2);
    }

    public static String getFormat(String filepath1, String filepath2) throws IOException {
        String absPath1 = Parser.makeAbsolutePath(filepath1).toString();
        String absPath2 = Parser.makeAbsolutePath(filepath2).toString();
        String formatFile1 = absPath1.substring(absPath1.indexOf(".") + 1);
        String formatFile2 = absPath2.substring(absPath2.indexOf(".") + 1);
        if (formatFile1.equals(formatFile2)) {
            return formatFile1;
        }
        throw new IOException("Different file formats");
    }
}
