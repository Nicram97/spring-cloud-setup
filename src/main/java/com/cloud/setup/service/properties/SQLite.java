package com.cloud.setup.service.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component()
public class SQLite {
    @Value("${sqlite.DB_DATABASE:#{null}}")
    private String database;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    @Override
    public String toString() {
        return "SQLite{" +
                "database='" + database + '\'' +
                '}';
    }
}
