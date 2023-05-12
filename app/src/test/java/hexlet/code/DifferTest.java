package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    private static String jsonFile1;
    private static String jsonFile2;
    private static String jsonParsingResult;
    @BeforeAll
    public static void beforeAll() throws Exception {
        jsonFile1 = "src/test/resources/file1.json";
        jsonFile2 = "src/test/resources/file2.json";
        jsonParsingResult = Files.readString(Paths.get("src/test/java/hexlet/code/jsonParsingResult"));
    }
    @Test
    public void jsonTest() throws Exception {
        String result = Differ.generate(jsonFile1, jsonFile2);
        assertThat(result).isEqualTo(jsonParsingResult);
    }
}

