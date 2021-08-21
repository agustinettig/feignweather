package com.sburble.feignweather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FeignweatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignweatherApplication.class, args);
	}

}
