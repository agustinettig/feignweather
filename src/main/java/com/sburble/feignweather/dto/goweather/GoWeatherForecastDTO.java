package com.sburble.feignweather.dto.goweather;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GoWeatherForecastDTO {

    private String day;
    private String temperature;
}
