package com.alisimsek.ReportingApi.service;

import com.alisimsek.ReportingApi.dto.request.TransactionRequest;
import com.alisimsek.ReportingApi.dto.response.client.ClientResponse;
import com.alisimsek.ReportingApi.dto.response.client.CustomerInfoDto;
import com.alisimsek.ReportingApi.exception.TokenRequiredException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private AuthService authService;

    @InjectMocks
    private ClientService clientService;

    @Value("${api.url.client}")
    private String API_URL_CLIENT;

    @Test
    void getClient_success(){

        // Mock request
        TransactionRequest mockRequest = new TransactionRequest("1067301-1675430916-3");

        // Mock Response
        ClientResponse expectedResponse = new ClientResponse();

        CustomerInfoDto customerInfoDto = new CustomerInfoDto();
        customerInfoDto.setId(752101L);

        LocalDateTime created_at = LocalDateTime.of(2023,02,03,13,28,37);
        customerInfoDto.setCreated_at(created_at);
        customerInfoDto.setEmail("a@b.com");

        expectedResponse.setCustomerInfo(customerInfoDto);

        ResponseEntity<ClientResponse> expectedResult = new ResponseEntity<>(expectedResponse, HttpStatus.OK);

        String mockAuthToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtZXJjaGFudFVzZXJJZCI6NTMsInJvbGUiOiJ1c2VyIiwibWVyY2hhbnRJZCI6Mywic3Vi" +
                "TWVyY2hhbnRJZHMiOlszLDc0LDkzLDExOTEsMTI5NSwxMTEsMTM3LDEzOCwxNDIsMTQ1LDE0NiwxNTMsMzM0LDE3NSwxODQsMjIwLDIyMSwyMjIsMjIzLDI5NCwzMjIs" +
                "MzIzLDMyNywzMjksMzMwLDM0OSwzOTAsMzkxLDQ1NSw0NTYsNDc5LDQ4OCw1NjMsMTE0OSw1NzAsMTEzOCwxMTU2LDExNTcsMTE1OCwxMTc5LDEyOTMsMTI5NCwxMzA2L" +
                "DEzMDcsMTMyNCwxMzMxLDEzMzgsMTMzOSwxMzQxLDEzNDYsMTM0NywxMzQ4LDEzNDksMTM1M10sInRpbWVzdGFtcCI6MTcxMDU4NDU0M30.yzHCINxR_G90gX5UUTTA1pOZrFL5aDTio95j5zTT8eg";

        when(authService.getAuthorizationHeader(any())).thenReturn(Optional.of(mockAuthToken));

        when(restTemplate.exchange(eq(API_URL_CLIENT),eq(HttpMethod.POST), any(HttpEntity.class), eq(ClientResponse.class)))
                .thenReturn(expectedResult);

        ClientResponse actualResponse = clientService.getClient(mockRequest);

        assertEquals(expectedResponse.getCustomerInfo(), actualResponse.getCustomerInfo());
    }

    @Test
    void getClient_headerIsEmpty(){

        // Mock request
        TransactionRequest mockRequest = new TransactionRequest("1067301-1675430916-3");

        when(authService.getAuthorizationHeader(any())).thenReturn(Optional.of(""));

        assertThrows(TokenRequiredException.class,
                () ->{
                    ClientResponse actualResponse = clientService.getClient(mockRequest);
                });
    }

    @Test
    void getClient_headerIsNull(){

        // Mock request
        TransactionRequest mockRequest = new TransactionRequest("1067301-1675430916-3");

        when(authService.getAuthorizationHeader(any())).thenReturn(Optional.empty());

        assertThrows(TokenRequiredException.class,
                () ->{
                    ClientResponse actualResponse = clientService.getClient(mockRequest);
                });
    }
}
