package com.example.demo.service;

import com.example.demo.entity.GeoCodingCoordinateEntity;
import com.example.demo.entity.OpenWeatherResponseEntity;
import com.example.demo.model.*;
import com.example.demo.provider.GeoCodingProvider;
import com.example.demo.provider.WeatherProvider;
import com.example.demo.transformer.GeocodingCoordinatesTransformer;
import com.example.demo.transformer.OpenWeatherTransformer;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImplementation implements WeatherService{

    private final GeoCodingProvider geocodingProvider;
    private final GeocodingCoordinatesTransformer geocodingCoordinatesTransformer;
    private final WeatherProvider weatherProvider;
    private final OpenWeatherTransformer openWeatherTransformer;

    public WeatherServiceImplementation(GeoCodingProvider geocodingProvider, GeocodingCoordinatesTransformer geocodingCoordinatesTransformer, WeatherProvider weatherProvider, OpenWeatherTransformer openWeatherTransformer) {
        this.geocodingProvider = geocodingProvider;
        this.geocodingCoordinatesTransformer = geocodingCoordinatesTransformer;
        this.weatherProvider = weatherProvider;
        this.openWeatherTransformer = openWeatherTransformer;
    }

    @Override
    public WeatherResponse getWeather(WeatherRequest weatherRequest) throws Exception {
        // get latitude and longitude for a city
        GeoCodingCoordinateEntity geoCoordinates = geocodingProvider.getCoordinates(weatherRequest);
        CityCoordinates cityCoordinates = geocodingCoordinatesTransformer
                .transformToCityCoordinates(geoCoordinates);

        // get the weather for the geo coordinates
        OpenWeatherResponseEntity openWeatherResponseEntity = weatherProvider.getWeather(cityCoordinates);
        CityWeather cityWeather = openWeatherTransformer.transformToCityWeather(openWeatherResponseEntity);

        // return to user
        return openWeatherTransformer.transformToWeatherResponse(cityWeather);
    }
}
