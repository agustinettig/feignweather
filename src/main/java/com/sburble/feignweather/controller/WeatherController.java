package com.sburble.feignweather.controller;

import com.sburble.feignweather.dto.LocationDTO;
import com.sburble.feignweather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
@Validated
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/zipcode/{zipCode}")
    public LocationDTO getForecastByZipCode(@PathVariable String zipCode) {
        return weatherService.getLocationWeatherByZipCode(zipCode);
    }
}
