package com.sburble.feignweather;

import com.sburble.feignweather.dto.goweather.GoWeatherDTO;
import com.sburble.feignweather.dto.goweather.GoWeatherForecastDTO;
import com.sburble.feignweather.dto.postmon.PostmonDTO;

import java.util.List;

public class ScenarioFactory {

    public static final PostmonDTO POSTMON_RESPONSE = getPostmonResponse();
    public static final GoWeatherDTO GOWEATHER_RESPONSE_WITH_FORECAST = getGoWeatherResponseWithForecast();
    public static final GoWeatherDTO GOWEATHER_RESPONSE_WITHOUT_FORECAST = getGoWeatherResponseWithoutForecast();

    private static PostmonDTO getPostmonResponse() {
        return PostmonDTO.builder()
                .state("SP")
                .city("São Paulo")
                .district("Ipiranga")
                .street("Test Street")
                .build();
    }

    private static GoWeatherDTO getGoWeatherResponseWithForecast() {
        return GoWeatherDTO.builder()
                .temperature("30")
                .forecast(List.of(GoWeatherForecastDTO.builder().temperature("31").day("1").build(),
                                GoWeatherForecastDTO.builder().temperature("32").day("2").build()))
                .build();
    }

    private static GoWeatherDTO getGoWeatherResponseWithoutForecast() {
        return GoWeatherDTO.builder()
                .temperature("30")
                .build();
    }
}
