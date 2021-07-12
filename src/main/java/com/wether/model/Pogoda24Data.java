package com.wether.model;

import com.wether.model.response.Pogoda24List;
import com.wether.model.response.Pogoda24Main;
import lombok.Data;

import java.util.List;

@Data
public class Pogoda24Data {
    public String city;
    //public List<Pogoda24Day> days;
    public List<Pogoda24Main> days;
    public String joke;
}
