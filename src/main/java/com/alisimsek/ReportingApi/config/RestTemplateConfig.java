package com.alisimsek.ReportingApi.config;

import com.alisimsek.ReportingApi.exception.RestTemplateResponseErrorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

    private final RestTemplateResponseErrorHandler responseErrorHandler;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .errorHandler(responseErrorHandler)
                .build();
    }
}