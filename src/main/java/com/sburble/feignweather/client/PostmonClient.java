package com.sburble.feignweather.client;

import com.sburble.feignweather.dto.postmon.PostmonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url="${postmon.url}", name="postmonClient")
public interface PostmonClient {

    @GetMapping("/cep/{zipCode}")
    PostmonDTO getLocationByZipCode(@PathVariable String zipCode);
}
