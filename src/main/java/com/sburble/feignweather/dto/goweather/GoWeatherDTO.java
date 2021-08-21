package com.sburble.feignweather.dto.goweather;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GoWeatherDTO {

    private String temperature;
    private List<GoWeatherForecastDTO> forecast;
}