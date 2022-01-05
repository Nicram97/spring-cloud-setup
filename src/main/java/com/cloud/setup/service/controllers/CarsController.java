package com.cloud.setup.service.controllers;

import com.cloud.setup.service.properties.AppProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RestController()
public class CarsController {
    private final AppProperties appProperties;
    private final HttpClient httpClient;

    CarsController(AppProperties appProperties) {
        this.appProperties = appProperties;
        this.httpClient = HttpClient.newHttpClient();
    }

    @GetMapping("/cars")
    public Object getRandomCar() throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s:%s",this.appProperties.getSubservice().getUrl(), this.appProperties.getSubservice().getPort().toString())))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        List<Object> jsonList = jsonStringToObjectArray(response.body());
        int randomValue = ThreadLocalRandom.current().nextInt(0, jsonList.size() + 1);
        return jsonList.get(randomValue);
    }

    @SuppressWarnings("rawtypes")
    public static <T> T jsonStringToObjectArray(String content) throws IOException {
        T obj = null;
        ObjectMapper mapper = new ObjectMapper();
        obj = (T) mapper.readValue(content, new TypeReference<List>() {
        });
        return obj;
    }
}
