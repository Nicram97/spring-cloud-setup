package com.cloud.setup.service.controllers;

import com.cloud.setup.service.properties.AppProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@Slf4j
public class HelloWorld {
    private final AppProperties appProperties;

    HelloWorld(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @GetMapping("/")
    public String helloWorld() {
        log.info(appProperties.toString());
        return "Hello world";
    }
}
