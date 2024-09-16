package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityWeather {
    private String weather;
    private String details;
}
