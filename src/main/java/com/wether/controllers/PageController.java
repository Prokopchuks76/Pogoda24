package com.wether.controllers;

import com.wether.model.Pogoda24Data;
import com.wether.service.Pogoda24Service;
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
    public String homePage() {
        return "home";
   }

    @GetMapping("/api")
    public ModelAndView getWeather(@RequestParam(value = "city") String city) throws IOException, InterruptedException {
        ModelAndView mav = new ModelAndView("home");
        ResponseEntity<Pogoda24Data> data = service.getData(city);
        Pogoda24Data p24 = data.getBody();
        System.out.println("@@@:" + p24.getDays());
        mav.addObject("cityty", p24.getCity());
        mav.addObject("day", p24.getDays());
        mav.addObject("jokeke", p24.getJoke());
        return mav;
    }
}