package dev.danielblasina.config.parsers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import org.junit.Test;

public class YamlParserTest {
    @Test public void testCanParseYaml() throws IOException {
        var data = new YamlParser().parse();
        assertNotNull(data);
        assertEquals("test", data.database());
    }
}