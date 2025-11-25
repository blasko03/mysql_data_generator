package dev.danielblasina.config.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import dev.danielblasina.config.DBConfig;
import dev.danielblasina.config.DBconfigParser;
import java.io.IOException;
import java.nio.file.Path;

public class YamlParser implements DBconfigParser {
    static final Path FILE_PATH = Path.of("config", "config.yml");

    @Override
    public DBConfig parse() throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(FILE_PATH.toFile(), DBConfig.class);
    }
}
