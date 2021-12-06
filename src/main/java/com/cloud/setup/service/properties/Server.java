package com.cloud.setup.service.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component()
public class Server {
    @Value("${server.PORT:#{null}}")
    private Integer port;

    public Integer getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Server{" +
                "port=" + port +
                '}';
    }
}
