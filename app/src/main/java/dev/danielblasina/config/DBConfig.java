package dev.danielblasina.config;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public record DBConfig(String database, String hostname, String username, String password, Integer port) {
    public static DBConfig parseFrom(DBconfigParser parser) throws IOException {
        return parser.parse();
    }

    public URI getUri() throws URISyntaxException {
        return new URI("jdbc:mysql", null, hostname, port, "/" + database, null, null);
    }
}
