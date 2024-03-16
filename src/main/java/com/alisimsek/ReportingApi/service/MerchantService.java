package com.alisimsek.ReportingApi.service;

import com.alisimsek.ReportingApi.dto.request.UserLoginRequest;
import com.alisimsek.ReportingApi.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final RestTemplate restTemplate;

    @Value("${api.url.login}")
    private String API_URL_LOGIN;

    public UserResponse login (UserLoginRequest userLoginRequest){

            UserResponse userResponse = restTemplate.postForObject(API_URL_LOGIN, userLoginRequest, UserResponse.class);
            return userResponse;
    }
}
