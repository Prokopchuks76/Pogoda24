package com.wether.model;

import lombok.Data;

import java.util.List;

@Data
public class Pogoda24Day {
    public List<Pogoda24TimePoint> timePoints;
}
