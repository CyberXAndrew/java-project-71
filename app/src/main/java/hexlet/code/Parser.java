package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String fileContent, String fileFormat) throws IOException {
        ObjectMapper objectMapper = buildMapper(fileFormat);
        return objectMapper.readValue(fileContent, new TypeReference<>() { });
    }
    public static ObjectMapper buildMapper(String format) throws IOException {
        if (format.equals("json")) {
            return new ObjectMapper();
        } else if (format.equals("yaml") || format.equals("yml")) {
            return new ObjectMapper(new YAMLFactory());
        } else {
            throw new IOException("Unknown output format");
        }
    }
}
