package com.alisimsek.ReportingApi.service;

import com.alisimsek.ReportingApi.dto.request.UserLoginRequest;
import com.alisimsek.ReportingApi.dto.response.UserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MerchantServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MerchantService merchantService;

    @Value("${api.url.login}")
    private String API_URL_LOGIN;

    @Test
    void login_success(){

        UserLoginRequest mockRequest = new UserLoginRequest("email@mail.com","password123");

        String mockAuthToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtZXJjaGFudFVzZXJJZCI6NTMsInJvbGUiOiJ1c2VyIiwibWVyY2hhbnRJZCI6Mywic3Vi" +
                "TWVyY2hhbnRJZHMiOlszLDc0LDkzLDExOTEsMTI5NSwxMTEsMTM3LDEzOCwxNDIsMTQ1LDE0NiwxNTMsMzM0LDE3NSwxODQsMjIwLDIyMSwyMjIsMjIzLDI5NCwzMjIs" +
                "MzIzLDMyNywzMjksMzMwLDM0OSwzOTAsMzkxLDQ1NSw0NTYsNDc5LDQ4OCw1NjMsMTE0OSw1NzAsMTEzOCwxMTU2LDExNTcsMTE1OCwxMTc5LDEyOTMsMTI5NCwxMzA2L" +
                "DEzMDcsMTMyNCwxMzMxLDEzMzgsMTMzOSwxMzQxLDEzNDYsMTM0NywxMzQ4LDEzNDksMTM1M10sInRpbWVzdGFtcCI6MTcxMDU4NDU0M30.yzHCINxR_G90gX5UUTTA1pOZrFL5aDTio95j5zTT8eg";

        UserResponse expectedResponse = new UserResponse(mockAuthToken, "approved");

        when(restTemplate.postForObject(eq(API_URL_LOGIN),eq(mockRequest),eq(UserResponse.class)))
                .thenReturn(expectedResponse);

        UserResponse actualResponse = merchantService.login(mockRequest);

        assertEquals(expectedResponse.getToken(),actualResponse.getToken());
        assertEquals(expectedResponse.getStatus(),actualResponse.getStatus());
    }
}