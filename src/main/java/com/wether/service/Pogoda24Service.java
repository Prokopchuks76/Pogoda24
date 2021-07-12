package com.wether.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wether.model.Pogoda24Data;
import com.wether.model.response.Pogoda24Joke;
import com.wether.model.response.Pogoda24List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class Pogoda24Service {

    private static final HttpClient httpClient = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_1_1)
        .connectTimeout(Duration.ofSeconds(10))
        .build();

    public ResponseEntity<Pogoda24Data> getData(String cityName) throws IOException, InterruptedException {
        Pogoda24Data data = new Pogoda24Data();
        data.setCity(cityName);

        getJoke(data);
        getWeather(data);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(data);
    }

    private void getJoke(Pogoda24Data data) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create("http://rzhunemogu.ru/RandJSON.aspx?CType=1"))
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        Pogoda24Joke joke = mapper.readValue(response.body(), Pogoda24Joke.class);
        data.setJoke(joke.getContent());
    }

    private void getWeather(Pogoda24Data data) throws IOException, InterruptedException {
        String url =
            "http://api.openweathermap.org/data/2.5/forecast?q=" +
            data.city +
            "&appid=38cd83ce1ab17bb6ebf0b7a18462e266&units=metric&lang=ua";

        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(url))
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Pogoda24List list = mapper.readValue(response.body(), Pogoda24List.class);
        data.setDays(list.getList());
    }
}

