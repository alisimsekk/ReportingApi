package com.alisimsek.ReportingApi.service;

import com.alisimsek.ReportingApi.dto.request.TransactionRequest;
import com.alisimsek.ReportingApi.dto.response.client.ClientResponse;
import com.alisimsek.ReportingApi.exception.TokenRequiredException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.http.HttpHeaders;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final RestTemplate restTemplate;
    private final AuthService authService;

    @Value("${api.url.client}")
    private String API_URL_CLIENT;

    public ClientResponse getClient(TransactionRequest transactionRequest) {
        Optional<String> authorizationHeader = authService.getAuthorizationHeader((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());

        if (authorizationHeader.isEmpty() || authorizationHeader.get().equals("")){
            throw new TokenRequiredException("Token is required");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader.get());

        HttpEntity<TransactionRequest> requestEntity = new HttpEntity<>(transactionRequest,headers);

        ResponseEntity<ClientResponse> responseEntity = restTemplate.exchange(
                API_URL_CLIENT, HttpMethod.POST,requestEntity, ClientResponse.class);

        return responseEntity.getBody();
    }
}