package com.cloud.setup.service.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="app")
public class AppProperties {
    private Server server;
    private InfluxClient influxClient;
    private Database database;
    private Logstash logstash;
    private SQLite sqLite;
    private Subservice subservice;

    public AppProperties(Server server,
                         InfluxClient influxClient,
                         Database database,
                         Logstash logstash,
                         SQLite sqLite,
                         Subservice subservice
    ) {
        this.server = server.getPort() != null ? server : null;
        this.influxClient = influxClient.getInfluxUrl() != null ? influxClient : null;
        this.database = database.getDbDatabase() != null ? database : null;
        this.logstash = logstash.getHost() != null ? logstash : null;
        this.sqLite = sqLite.getDatabase() != null ? sqLite : null;
        this.subservice = subservice.getUrl() != null ? subservice : null;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public InfluxClient getInfluxClient() {
        return influxClient;
    }

    public void setInfluxClient(InfluxClient influxClient) {
        this.influxClient = influxClient;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public Logstash getLogstash() {
        return logstash;
    }

    public void setLogstash(Logstash logstash) {
        this.logstash = logstash;
    }

    public SQLite getSqLite() {
        return sqLite;
    }

    public void setSqLite(SQLite sqLite) {
        this.sqLite = sqLite;
    }

    public Subservice getSubservice() {
        return subservice;
    }

    public void setSubservice(Subservice subservice) {
        this.subservice = subservice;
    }

    @Override
    public String toString() {
        return "AppProperties{" +
                "server=" + server +
                ", influxClient=" + influxClient +
                ", database=" + database +
                ", logstash=" + logstash +
                ", sqLite=" + sqLite +
                ", subservice=" + subservice +
                '}';
    }
}
