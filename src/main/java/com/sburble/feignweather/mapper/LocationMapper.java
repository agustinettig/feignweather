package com.sburble.feignweather.mapper;

import com.sburble.feignweather.dto.ForecastDTO;
import com.sburble.feignweather.dto.LocationDTO;
import com.sburble.feignweather.dto.goweather.GoWeatherDTO;
import com.sburble.feignweather.dto.postmon.PostmonDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationMapper {

    private LocationDTO location;

    public LocationMapper() {
        location = new LocationDTO();
    }

    public LocationDTO map(PostmonDTO postmonResponse, GoWeatherDTO weatherResponse) {
        mapLocation(postmonResponse);
        mapForecast(weatherResponse);
        return location;
    }

    private void mapLocation(PostmonDTO postmonDTO) {
        if (postmonDTO == null) {
            return;
        }

        location.setState(postmonDTO.getState());
        location.setCity(postmonDTO.getCity());
        location.setDistrict(postmonDTO.getDistrict());
        location.setStreet(postmonDTO.getStreet());
    }

    private void mapForecast(GoWeatherDTO goWeatherDTO) {
        if (goWeatherDTO == null) {
            return;
        }

        List<ForecastDTO> forecastList = new ArrayList<>();
        LocalDate today = LocalDate.now();

        forecastList.add(ForecastDTO.builder()
                .date(today)
                .temperature(goWeatherDTO.getTemperature())
                .build());

        if (goWeatherDTO.getForecast() != null) {
            forecastList.addAll(goWeatherDTO.getForecast().stream().map(goWeatherForecast -> ForecastDTO.builder()
                    .date(today.plusDays(Long.valueOf(goWeatherForecast.getDay())))
                    .temperature(goWeatherForecast.getTemperature())
                    .build()).collect(Collectors.toList()));
        }

        location.setForecast(forecastList);
    }
}

