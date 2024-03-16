package com.alisimsek.ReportingApi.service;

import com.alisimsek.ReportingApi.dto.request.TransactionReportRequest;
import com.alisimsek.ReportingApi.dto.request.TransactionRequest;
import com.alisimsek.ReportingApi.dto.response.transaction.TransactionResponse;
import com.alisimsek.ReportingApi.dto.response.transactionReport.TransactionReportResponse;
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

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final RestTemplate restTemplate;

    @Value("${api.url.transaction}")
    private String API_URL_TRANSACTION;

    @Value("${api.url.transactions.report}")
    private String API_URL_TRANSACTION_REPORT;

    public TransactionResponse getTransaction(TransactionRequest transactionRequest) {
        String authorizationHeader = getAuthorizationHeader();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);

        HttpEntity<TransactionRequest> requestEntity = new HttpEntity<>(transactionRequest,headers);

        ResponseEntity<TransactionResponse> responseEntity = restTemplate.exchange(
                API_URL_TRANSACTION, HttpMethod.POST,requestEntity, TransactionResponse.class);

        return responseEntity.getBody();
    }

    public TransactionReportResponse getTransactionReport(TransactionReportRequest transactionReportRequest) {
        String authorizationHeader = getAuthorizationHeader();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);

        HttpEntity<TransactionReportRequest> requestEntity = new HttpEntity<>(transactionReportRequest,headers);

        ResponseEntity<TransactionReportResponse> responseEntity = restTemplate.exchange(
                API_URL_TRANSACTION_REPORT, HttpMethod.POST,requestEntity, TransactionReportResponse.class);

        return responseEntity.getBody();

    }

    private String getAuthorizationHeader() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return attributes.getRequest().getHeader("Authorization");
        }
        return null;
    }
}
