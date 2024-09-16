package com.example.demo.transformer;

import com.example.demo.entity.OpenWeatherResponseEntity;
import com.example.demo.model.CityWeather;
import com.example.demo.model.WeatherResponse;
import org.springframework.stereotype.Service;

@Service
public class OpenWeatherTransformer {
    public CityWeather transformToCityWeather(OpenWeatherResponseEntity openWeatherResponseEntity) {
        return CityWeather.builder()
                .weather(openWeatherResponseEntity.getWeather()[0].getMain())
                .details(openWeatherResponseEntity.getWeather()[0].getDescription())
                .build();
    }

    public WeatherResponse transformToWeatherResponse(CityWeather cityWeather) {
        return WeatherResponse.builder()
                .weather(cityWeather.getWeather())
                .details(cityWeather.getDetails())
                .build();
    }

}
