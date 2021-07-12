package com.wether.model.response;

import lombok.Data;

@Data
public class Pogoda24Main {
    public Pogoda24Temp main;
    public String dt_txt;
    public long dt;
}
