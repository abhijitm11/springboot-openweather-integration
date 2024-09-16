package com.example.demo.service;

import com.example.demo.model.WeatherRequest;
import com.example.demo.model.WeatherResponse;

public interface WeatherService {
    WeatherResponse getWeather(WeatherRequest weatherRequest) throws Exception;
}
