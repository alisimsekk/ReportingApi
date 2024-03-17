package com.alisimsek.ReportingApi.service;

import com.alisimsek.ReportingApi.dto.request.TransactionReportRequest;
import com.alisimsek.ReportingApi.dto.request.TransactionRequest;
import com.alisimsek.ReportingApi.dto.response.transaction.TransactionResponse;
import com.alisimsek.ReportingApi.dto.response.transactionReport.TransactionReportResponse;
import com.alisimsek.ReportingApi.exception.TokenRequiredException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final RestTemplate restTemplate;
    private final AuthService authService;

    @Value("${api.url.transaction}")
    private String API_URL_TRANSACTION;

    @Value("${api.url.transactions.report}")
    private String API_URL_TRANSACTION_REPORT;

    public TransactionResponse getTransaction(TransactionRequest transactionRequest) {
        Optional<String> authorizationHeader = authService.getAuthorizationHeader((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());

        if (authorizationHeader.isEmpty() || authorizationHeader.get().equals("")){
            throw new TokenRequiredException("Token is required");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader.get());

        HttpEntity<TransactionRequest> requestEntity = new HttpEntity<>(transactionRequest,headers);

        ResponseEntity<TransactionResponse> responseEntity = restTemplate.exchange(
                API_URL_TRANSACTION, HttpMethod.POST,requestEntity, TransactionResponse.class);

        return responseEntity.getBody();
    }

    public TransactionReportResponse getTransactionReport(TransactionReportRequest transactionReportRequest) {
        Optional<String> authorizationHeader = authService.getAuthorizationHeader((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());

        if (authorizationHeader.isEmpty() || authorizationHeader.get().equals("")){
            throw new TokenRequiredException("Token is required");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader.get());

        HttpEntity<TransactionReportRequest> requestEntity = new HttpEntity<>(transactionReportRequest,headers);

        ResponseEntity<TransactionReportResponse> responseEntity = restTemplate.exchange(
                API_URL_TRANSACTION_REPORT, HttpMethod.POST,requestEntity, TransactionReportResponse.class);

        return responseEntity.getBody();
    }
}