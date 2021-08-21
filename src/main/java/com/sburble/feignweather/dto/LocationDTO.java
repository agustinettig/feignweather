package com.sburble.feignweather.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {

    private String district;
    private String city;
    private String state;
    private String street;
    private List<ForecastDTO> forecast;
}
