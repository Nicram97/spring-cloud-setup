package com.cloud.setup.service.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component()
public class Database {
    @Value("${database.DB_HOST:#{null}}")
    private String dbHost;
    @Value("${database.DB_PORT:#{null}}")
    private Integer dbPort;
    @Value("${database.DB_USERNAME:#{null}}")
    private String dbUsername;
    @Value("${database.DB_PASSWORD:#{null}}")
    private String dbPassword;
    @Value("${database.DB_DATABASE:#{null}}")
    private String dbDatabase;

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public Integer getDbPort() {
        return dbPort;
    }

    public void setDbPort(Integer dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbDatabase() {
        return dbDatabase;
    }

    public void setDbDatabase(String dbDatabase) {
        this.dbDatabase = dbDatabase;
    }

    @Override
    public String toString() {
        return "Database{" +
                "dbHost='" + dbHost + '\'' +
                ", dbPort=" + dbPort +
                ", dbUsername='" + dbUsername + '\'' +
                ", dbPassword='" + dbPassword + '\'' +
                ", dbDatabase='" + dbDatabase + '\'' +
                '}';
    }
}
