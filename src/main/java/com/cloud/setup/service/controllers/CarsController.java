package com.cloud.setup.service.controllers;

import com.cloud.setup.service.properties.AppProperties;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
    public JsonElement getRandomCar() throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s:%s",this.appProperties.getSubservice().getUrl(), this.appProperties.getSubservice().getPort().toString())))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        JsonArray jsonArray = (JsonArray) JsonParser.parseString(response.body());
        int randomValue = ThreadLocalRandom.current().nextInt(0, jsonArray.size() + 1);
        return jsonArray.get(randomValue);
    }
}
