package com.sburble.feignweather.service;

import com.sburble.feignweather.client.GoWeatherClient;
import com.sburble.feignweather.client.PostmonClient;
import com.sburble.feignweather.dto.LocationDTO;
import com.sburble.feignweather.dto.goweather.GoWeatherDTO;
import com.sburble.feignweather.dto.postmon.PostmonDTO;
import com.sburble.feignweather.exception.DataNotFoundException;
import com.sburble.feignweather.mapper.LocationMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final PostmonClient postmonClient;

    private final GoWeatherClient goWeatherClient;

    private final LocationMapper locationMapper;

    public LocationDTO getLocationWeatherByZipCode(String zipCode) {
        try {
            PostmonDTO postmonResponse =  postmonClient.getLocationByZipCode(zipCode);
            GoWeatherDTO weatherResponse = goWeatherClient.getWeatherByCity(postmonResponse.getDistrict());
            return locationMapper.map(postmonResponse, weatherResponse);
        } catch (FeignException e) {
            throw new DataNotFoundException();
        }
    }
}
