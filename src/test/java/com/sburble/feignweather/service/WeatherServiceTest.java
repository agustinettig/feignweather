package com.sburble.feignweather.service;

import com.sburble.feignweather.ScenarioFactory;
import com.sburble.feignweather.client.GoWeatherClient;
import com.sburble.feignweather.client.PostmonClient;
import com.sburble.feignweather.dto.goweather.GoWeatherDTO;
import com.sburble.feignweather.dto.goweather.GoWeatherForecastDTO;
import com.sburble.feignweather.dto.postmon.PostmonDTO;
import com.sburble.feignweather.exception.DataNotFoundException;
import com.sburble.feignweather.mapper.LocationMapper;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @InjectMocks
    private WeatherService weatherService;

    @Mock
    private PostmonClient postmonClient;

    @Mock
    private GoWeatherClient goWeatherClient;

    @Mock
    private LocationMapper locationMapper;

    @Test
    void getLocationWeatherByZipCode_whenDataFound_shouldBeMapped() {
        PostmonDTO postmonResponse = ScenarioFactory.POSTMON_RESPONSE;
        GoWeatherDTO goWeatherResponse = ScenarioFactory.GOWEATHER_RESPONSE_WITH_FORECAST;
        when(postmonClient.getLocationByZipCode("00000000")).thenReturn(postmonResponse);
        when(goWeatherClient.getWeatherByCity("São Paulo")).thenReturn(goWeatherResponse);

        weatherService.getLocationWeatherByZipCode("00000000");

        verify(locationMapper).map(postmonResponse, goWeatherResponse);
    }

    @Test
    void getLocationWeatherByZipCode_whenApiError_shouldThrowException() {
        when(postmonClient.getLocationByZipCode("00000000")).thenThrow(FeignException.class);

        assertThrows(DataNotFoundException.class, () -> weatherService.getLocationWeatherByZipCode("00000000"));
    }
}