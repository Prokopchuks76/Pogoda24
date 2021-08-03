package com.wether.controllers;

import com.wether.model.Pogoda24Data;
import com.wether.service.Pogoda24Service;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class PageController {

    @Autowired
    private Pogoda24Service service;

    @GetMapping("/")
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("cityty", Strings.EMPTY);
        return mav;
   }

    @GetMapping("/api")
    public ModelAndView getWeather(@RequestParam(value = "city") String city) throws IOException, InterruptedException {
        ModelAndView mav = new ModelAndView("home");
        ResponseEntity<Pogoda24Data> data = service.getData(city);
        Pogoda24Data p24 = data.getBody();
        mav.addObject("cityty", "Прогноз для " + p24.getCity());
        mav.addObject("days", p24.getDays());
        mav.addObject("jokeke", "Жарт: " + p24.getJoke());
        return mav;
    }
}