package com.cloud.setup.service.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component()
public class InfluxClient {
    @Value("${influxClient.INFLUX_URL:#{null}}")
    private String influxUrl;
    @Value("${influxClient.INFLUX_DATABASE:#{null}}")
    private String influxDatabase;
    @Value("${influxClient.INFLUX_RETENTION_POLICY:#{null}}")
    private String influxRetentionPolicy;
    @Value("${influxClient.INFLUX_ORGANIZATION:#{null}}")
    private String influxOrganization;
    @Value("${influxClient.INFLUX_SEND_INTERVAL_MS:#{null}}")
    private Integer influxSendIntervalMs;
    @Value("${influxClient.INFLUX_ACCESS_TOKEN:#{null}}")
    private String influxAccessToken;
    @Value("${influxClient.INFLUX_BUCKET_ID:#{null}}")
    private String influxBucketId;

    public String getInfluxUrl() {
        return influxUrl;
    }

    public void setInfluxUrl(String influxUrl) {
        this.influxUrl = influxUrl;
    }

    public String getInfluxDatabase() {
        return influxDatabase;
    }

    public void setInfluxDatabase(String influxDatabase) {
        this.influxDatabase = influxDatabase;
    }

    public String getInfluxRetentionPolicy() {
        return influxRetentionPolicy;
    }

    public void setInfluxRetentionPolicy(String influxRetentionPolicy) {
        this.influxRetentionPolicy = influxRetentionPolicy;
    }

    public String getInfluxOrganization() {
        return influxOrganization;
    }

    public void setInfluxOrganization(String influxOrganization) {
        this.influxOrganization = influxOrganization;
    }

    public Integer getInfluxSendIntervalMs() {
        return influxSendIntervalMs;
    }

    public void setInfluxSendIntervalMs(Integer influxSendIntervalMs) {
        this.influxSendIntervalMs = influxSendIntervalMs;
    }

    public String getInfluxAccessToken() {
        return influxAccessToken;
    }

    public void setInfluxAccessToken(String influxAccessToken) {
        this.influxAccessToken = influxAccessToken;
    }

    public String getInfluxBucketId() {
        return influxBucketId;
    }

    public void setInfluxBucketId(String influxBucketId) {
        this.influxBucketId = influxBucketId;
    }

    @Override
    public String toString() {
        return "InfluxClient{" +
                "influxUrl='" + influxUrl + '\'' +
                ", influxDatabase='" + influxDatabase + '\'' +
                ", influxRetentionPolicy='" + influxRetentionPolicy + '\'' +
                ", influxOrganization='" + influxOrganization + '\'' +
                ", influxSendIntervalMs='" + influxSendIntervalMs + '\'' +
                ", influxAccessToken='" + influxAccessToken + '\'' +
                ", influxBucketId='" + influxBucketId + '\'' +
                '}';
    }
}
