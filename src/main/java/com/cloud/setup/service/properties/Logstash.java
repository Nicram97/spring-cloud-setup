package com.cloud.setup.service.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component()
public class Logstash {
    @Value("${logstash.LOGSTASH_HOST:#{null}}")
    private String host;
    @Value("${logstash.LOGSTASH_PORT:#{null}}")
    private Integer port;
    @Value("${logstash.LOGSTASH_PROTOCOL:#{null}}")
    private String protocol;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public String toString() {
        return "Logstash{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", protocol='" + protocol + '\'' +
                '}';
    }
}
