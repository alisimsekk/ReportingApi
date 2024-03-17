package com.alisimsek.ReportingApi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Service
public class AuthService {

    public Optional<String> getAuthorizationHeader(ServletRequestAttributes attributes) {
        if (attributes != null) {
            return Optional.ofNullable(attributes.getRequest().getHeader("Authorization"));
        }
        return Optional.empty();
    }
}
