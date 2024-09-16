package com.example.demo.transformer;

import com.example.demo.model.CityCoordinates;
import com.example.demo.entity.GeoCodingCoordinateEntity;
import org.springframework.stereotype.Service;

@Service
public class GeocodingCoordinatesTransformer {
    public CityCoordinates transformToCityCoordinates(GeoCodingCoordinateEntity geoCoordinates) {
        return CityCoordinates.builder()
                .latitude(geoCoordinates.getLatitude())
                .longitude(geoCoordinates.getLongitude())
                .build();
    }
}
