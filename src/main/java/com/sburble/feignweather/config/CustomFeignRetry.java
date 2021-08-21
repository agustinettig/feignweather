package com.sburble.feignweather.config;

import com.sburble.feignweather.config.properties.RetryProperties;
import org.springframework.stereotype.Component;

import feign.RetryableException;
import feign.Retryer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomFeignRetry implements Retryer {

    private final RetryProperties retryProperties;

    private int attempt = 1;

    @Override
    public void continueOrPropagate(RetryableException e) {
        if (attempt > retryProperties.getMaxAttempts()) {
            log.error("API access attempts exhausted, message={}, cause={}, error={}", e.getMessage(), e.getCause(), e.getClass());
            throw e;
        }
        try {
            log.warn("Attempting to access API {} of {}, message={}, cause={}, error={}", attempt, retryProperties.getMaxAttempts(), e.getMessage(), e.getCause(), e.getClass());
            incrementAttempt();
            Thread.sleep(retryProperties.getDelay());
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }

    private void incrementAttempt() {
        attempt++;
    }

    @Override
    public Retryer clone() {
        return new CustomFeignRetry(retryProperties);
    }
}