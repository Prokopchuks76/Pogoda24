package com.wether.controllers;

import com.wether.service.Pogoda24Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Pogoda24Controller {

    @Autowired
    private Pogoda24Service service;

    /*@GetMapping("/api")
    public ResponseEntity test(@RequestParam("city") String city) throws IOException, InterruptedException {
        return service.getData(city);
    }*/
}
