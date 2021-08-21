# FEIGN WEATHER
SpringBoot project using OpenFeign to fetch location and weather data from two APIs.

## Main features
- Get location from a brazilian zipCode, and the weather based on
  results
- Map data from different APIs
- Custom Feign retry

## Endpoint
Weather info by zipcode
```GET /weather/zipcode/{zipCode}```

## Used APIs
- [Postmon](https://postmon.com.br/)
- [Weather Api](https://github.com/robertoduessmann/weather-api)
