package dev.danielblasina;

import dev.danielblasina.config.DBConfig;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public record Table(DBConfig dbConfig, String name) {

    private Connection getConnection() throws URISyntaxException, SQLException {
        return DriverManager.getConnection(dbConfig.getUri().toString(), dbConfig.username(), dbConfig.password());
    }

    private String procName() {
        return String.format("%s_proc", this.name);
    }

    private String tableName() {
        return name;
    }

    public void create() throws SQLException, URISyntaxException {
        try (var conn = getConnection()) {
            conn.createStatement().executeUpdate(String.format("DROP TABLE IF EXISTS %s", this.tableName()));

            conn.createStatement().executeUpdate(String.format("DROP PROCEDURE IF EXISTS %s", this.procName()));

            conn.createStatement().executeUpdate(String.format("""
                CREATE TABLE %s (
                      product_id int(11) NOT NULL AUTO_INCREMENT,
                      product_name varchar(150) NOT NULL,
                      PRIMARY KEY (`product_id`)
                )
                """, this.tableName()));

            conn.createStatement().executeUpdate(String.format("""
                CREATE PROCEDURE %s(iter INT)
                BEGIN
                  DECLARE p1 INT unsigned DEFAULT 0;
                  label1: LOOP
                    IF p1 < iter THEN
                      INSERT INTO %s(product_name) VALUES('afsdfdf');
                      SET p1 = p1 + 1;
                      ITERATE label1;
                    END IF;
                    LEAVE label1;
                  END LOOP label1;
                  SET @x = p1;
                END
                """, this.procName(), this.tableName()));
        }
    }

    public void populate(int records) throws SQLException, URISyntaxException {
        try (var conn = getConnection()) {
            try (var prep = conn.prepareStatement(String.format("CALL %s(?)", this.procName()))) {
                prep.setInt(1, records);
                prep.executeUpdate();
            }
        }
    }
}
