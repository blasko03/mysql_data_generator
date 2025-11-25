package dev.danielblasina.config;

import java.io.IOException;

public interface DBconfigParser {
    DBConfig parse() throws IOException;
}
