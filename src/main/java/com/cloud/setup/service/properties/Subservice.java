package com.cloud.setup.service.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component()
public class Subservice {
    @Value("${subservice.URL:#{null}}")
    private String url;
    @Value("${subservice.port:#{null}}")
    private Integer port;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Subservice{" +
                "url='" + url + '\'' +
                ", port=" + port +
                '}';
    }
}
