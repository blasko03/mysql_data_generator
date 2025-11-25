package dev.danielblasina.config;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.junit.Test;

public class DBConfigTest {
    static class TestParser implements DBconfigParser {
        @Override
        public DBConfig parse() {
            return new DBConfig("db", "host", "user", "pass", 2345);
        }
    }

    @Test public void testISAbleToReturnUri() throws URISyntaxException, IOException {
        var expextedUri = new URI("jdbc:mysql", null, "host", 2345, "/" + "db", null, null);

        assertEquals(expextedUri, DBConfig.parseFrom(new TestParser()).getUri());
    }
}