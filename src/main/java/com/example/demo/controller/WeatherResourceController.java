package com.example.demo.controller;

import com.example.demo.model.WeatherRequest;
import com.example.demo.model.WeatherResponse;
import com.example.demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class WeatherResourceController {
    private final WeatherService weatherService;
    public WeatherResourceController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather/{city}")
    public WeatherResponse weather(@PathVariable String city) throws Exception {
        WeatherRequest weatherRequest = WeatherRequest.builder()
                .city(city)
                .build();
        return weatherService.getWeather(weatherRequest);
    }

}
