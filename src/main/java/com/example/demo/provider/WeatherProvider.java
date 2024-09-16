package com.example.demo.provider;

import com.example.demo.entity.GeoCodingCoordinateEntity;
import com.example.demo.entity.OpenWeatherResponseEntity;
import com.example.demo.model.CityCoordinates;
import com.example.demo.model.CityWeather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Service
public class WeatherProvider {
    @Value("${api.key}")
    private String apiKey;

    @Value("${weather.url}")
    private String weatherUrl;

    private RestTemplate restTemplate;

    public WeatherProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OpenWeatherResponseEntity getWeather(CityCoordinates cityCoordinates) throws Exception {
        //OpenWeather api call and get back the weather details -> https://api.openweathermap.org/data/2.5/weather
        HttpEntity<String> requestEntity = new HttpEntity<>(null,null);

        ResponseEntity<OpenWeatherResponseEntity> responseEntity;

        // sample format: https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid={API key}
        UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(weatherUrl)
                .queryParam("lat", cityCoordinates.getLatitude())
                .queryParam("lon", cityCoordinates.getLongitude())
                .queryParam("appid", apiKey)
                .build();
        try{
            responseEntity = restTemplate
                    .exchange(uriBuilder.toString(),
                            HttpMethod.GET,
                            requestEntity,
                            OpenWeatherResponseEntity.class);
        } catch (HttpStatusCodeException e) {
            throw new Exception(e.getMessage());
        }
        return responseEntity.getBody();
    }

}
