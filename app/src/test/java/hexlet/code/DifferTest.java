package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    private static final String DEFAULT_FORMAT = "stylish";
    private static final String PLAIN_FORMAT = "plain";
    private static String jsonFile1;
    private static String jsonFile2;
    private static String yamlFile3;
    private static String yamlFile4;
    private static String stylishParsingResult;
    private static String plainParsingResult;
    @BeforeAll
    public static void beforeAll() throws Exception {
//        defaultFormat = "stylish";
        jsonFile1 = "src/test/resources/file1.json";
        jsonFile2 = "src/test/resources/file2.json";
        yamlFile3 = "src/test/resources/file3.yaml";
        yamlFile4 = "src/test/resources/file4.yaml";
        stylishParsingResult = Files.readString(Paths.get("src/test/java/hexlet/code/jsonParsingResult"));
        plainParsingResult = Files.readString(Paths.get("src/test/java/hexlet/code/yamlParsingResult"));
    }
    @Test
    public void defaultFormatTest() throws Exception {
        String jsonResult = Differ.generate(jsonFile1, jsonFile2);
        assertThat(jsonResult).isEqualTo(stylishParsingResult);
        String yamlResult = Differ.generate(yamlFile3, yamlFile4);
        assertThat(yamlResult).isEqualTo(stylishParsingResult);
    }
    @Test
    public void stylishFormatTest() throws Exception {
        String jsonResult = Differ.generate(jsonFile1, jsonFile2, DEFAULT_FORMAT);
        assertThat(jsonResult).isEqualTo(stylishParsingResult);
        String yamlResult = Differ.generate(yamlFile3, yamlFile4, DEFAULT_FORMAT);
        assertThat(yamlResult).isEqualTo(stylishParsingResult);
    }

    @Test
    public void plainFormatTest() throws Exception {
        String jsonResult = Differ.generate(jsonFile1, jsonFile2, PLAIN_FORMAT);
        assertThat(jsonResult).isEqualTo(plainParsingResult);
        String yamlResult = Differ.generate(yamlFile3, yamlFile4, PLAIN_FORMAT);
        assertThat(yamlResult).isEqualTo(plainParsingResult);
    }
    @Test
    public void diffTestPlain() throws Exception {

    }
}

