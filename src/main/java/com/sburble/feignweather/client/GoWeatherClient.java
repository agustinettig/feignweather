package com.sburble.feignweather.client;

import com.sburble.feignweather.dto.goweather.GoWeatherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url="${go-weather.url}", name="goWeatherClient")
public interface GoWeatherClient {

    @GetMapping("/weather/{city}")
    GoWeatherDTO getWeatherByCity(@PathVariable String city);
}
