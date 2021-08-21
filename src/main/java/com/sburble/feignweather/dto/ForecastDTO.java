package com.sburble.feignweather.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ForecastDTO {

    private LocalDate date;
    private String temperature;
}
