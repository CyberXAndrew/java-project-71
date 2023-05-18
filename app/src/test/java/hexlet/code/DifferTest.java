package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    private static String defaultFormat;
    private static String jsonFile1;
    private static String jsonFile2;
    private static String jsonParsingResult;
    private static String yamlFile3;
    private static String yamlFile4;
    private static String yamlParsingResult;
    @BeforeAll
    public static void beforeAll() throws Exception {
        defaultFormat = "stylish";
        jsonFile1 = "src/test/resources/file1.json";
        jsonFile2 = "src/test/resources/file2.json";
        yamlFile3 = "src/test/resources/file3.yaml";
        yamlFile4 = "src/test/resources/file4.yaml";
        jsonParsingResult = Files.readString(Paths.get("src/test/java/hexlet/code/jsonParsingResult"));
        yamlParsingResult = Files.readString(Paths.get("src/test/java/hexlet/code/yamlParsingResult"));
    }
    @Test
    public void jsonTest() throws Exception {
        String result = Differ.generate(jsonFile1, jsonFile2, defaultFormat);
        assertThat(result).isEqualTo(jsonParsingResult);
    }
    @Test
    public void yamlTest() throws Exception {
        String result = Differ.generate(yamlFile3, yamlFile4, defaultFormat);
        assertThat(result).isEqualTo(yamlParsingResult);
    }
}

