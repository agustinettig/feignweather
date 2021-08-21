package com.sburble.feignweather.mapper;

import com.sburble.feignweather.ScenarioFactory;
import com.sburble.feignweather.dto.LocationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LocationMapperTest {

    private LocationMapper locationMapper;

    @BeforeEach
    void init() {
        locationMapper = new LocationMapper();
    }

    @Test
    void map_whenLocation_expectLocationToBeMapped() {
        LocationDTO mappedLocation = locationMapper.map(ScenarioFactory.POSTMON_RESPONSE, null);

        assertNotNull(mappedLocation);

        assertEquals(ScenarioFactory.POSTMON_RESPONSE.getState(), mappedLocation.getState());
        assertEquals(ScenarioFactory.POSTMON_RESPONSE.getCity(), mappedLocation.getCity());
        assertEquals(ScenarioFactory.POSTMON_RESPONSE.getDistrict(), mappedLocation.getDistrict());
        assertEquals(ScenarioFactory.POSTMON_RESPONSE.getStreet(), mappedLocation.getStreet());
    }

    @Test
    void map_whenNoLocation_expectLocationToBeEmpty() {
        LocationDTO mappedLocation = locationMapper.map(null, null);

        assertNotNull(mappedLocation);

        assertNull(mappedLocation.getState());
        assertNull(mappedLocation.getCity());
        assertNull(mappedLocation.getDistrict());
        assertNull(mappedLocation.getStreet());
    }

    @Test
    void map_whenWeatherHasNoForecastList_expectToBeMappedWithoutNextDays() {
        LocationDTO mappedLocation = locationMapper.map(null, ScenarioFactory.GOWEATHER_RESPONSE_WITHOUT_FORECAST);

        assertNotNull(mappedLocation);

        assertNotNull(mappedLocation.getForecast());
        assertEquals(1, mappedLocation.getForecast().size());
        assertEquals(ScenarioFactory.GOWEATHER_RESPONSE_WITHOUT_FORECAST.getTemperature(), mappedLocation.getForecast().get(0).getTemperature());
        assertEquals(LocalDate.now(), mappedLocation.getForecast().get(0).getDate());
    }

    @Test
    void map_whenMultipleWeather_expectForecastToBeMapped() {
        LocationDTO mappedLocation = locationMapper.map(null, ScenarioFactory.GOWEATHER_RESPONSE_WITH_FORECAST);

        assertNotNull(mappedLocation);

        assertNotNull(mappedLocation.getForecast());
        assertEquals(3, mappedLocation.getForecast().size());

        assertEquals(ScenarioFactory.GOWEATHER_RESPONSE_WITH_FORECAST.getTemperature(), mappedLocation.getForecast().get(0).getTemperature());
        assertEquals(LocalDate.now(), mappedLocation.getForecast().get(0).getDate());

        assertEquals(ScenarioFactory.GOWEATHER_RESPONSE_WITH_FORECAST.getForecast().get(0).getTemperature(), mappedLocation.getForecast().get(1).getTemperature());
        assertEquals(LocalDate.now().plusDays(1), mappedLocation.getForecast().get(1).getDate());

        assertEquals(ScenarioFactory.GOWEATHER_RESPONSE_WITH_FORECAST.getForecast().get(1).getTemperature(), mappedLocation.getForecast().get(2).getTemperature());
        assertEquals(LocalDate.now().plusDays(2), mappedLocation.getForecast().get(2).getDate());
    }

    @Test
    void map_whenNoWeather_expectForecastToBeEmpty() {
        LocationDTO mappedLocation = locationMapper.map(null, null);

        assertNotNull(mappedLocation);

        assertNull(mappedLocation.getForecast());
    }

}