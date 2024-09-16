package com.example.demo.provider;

import com.example.demo.entity.GeoCodingCoordinateEntity;
import com.example.demo.model.WeatherRequest;
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
public class GeoCodingProvider {
    @Value("${api.key}")
    private String apiKey;

    @Value("${geocoding.url}")
    private String geocodingUrl;

    private final RestTemplate restTemplate;

    public GeoCodingProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GeoCodingCoordinateEntity getCoordinates(WeatherRequest weatherRequest) throws Exception {
        //geocoding api call and get back the coordinates -> https://openweathermap.org/api/geocoding-api
        HttpEntity<String> requestEntity = new HttpEntity<>(null,null);

        ResponseEntity<GeoCodingCoordinateEntity[]> responseEntity;

        // sample format: http://api.openweathermap.org/geo/1.0/direct?q={city name},{state code},{country code}&limit={limit}&appid={API key
        UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(geocodingUrl)
                .queryParam("q", weatherRequest.getCity())
                .queryParam("limit", 1)
                .queryParam("appid", apiKey)
                .build();
        try{
            responseEntity = restTemplate
                    .exchange(uriBuilder.toString(),
                            HttpMethod.GET,
                            requestEntity,
                            GeoCodingCoordinateEntity[].class);
        } catch (HttpStatusCodeException e) {
            throw new Exception(e.getMessage());
        }
        return responseEntity.getBody()[0];
    }
}
