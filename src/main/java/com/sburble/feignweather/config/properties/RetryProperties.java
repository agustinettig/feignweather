package com.sburble.feignweather.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "retry")
public class RetryProperties {

    private int maxAttempts;
    private long delay;
}
