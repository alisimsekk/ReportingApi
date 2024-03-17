package com.alisimsek.ReportingApi.service;

import com.alisimsek.ReportingApi.dto.request.TransactionReportRequest;
import com.alisimsek.ReportingApi.dto.request.TransactionRequest;
import com.alisimsek.ReportingApi.dto.response.client.CustomerInfoDto;
import com.alisimsek.ReportingApi.dto.response.transaction.*;
import com.alisimsek.ReportingApi.dto.response.transactionReport.ResponseItemDto;
import com.alisimsek.ReportingApi.dto.response.transactionReport.TransactionReportResponse;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private  AuthService authService;

    @InjectMocks
    private TransactionService transactionService;

    @Value("${api.url.transaction}")
    private String API_URL_TRANSACTION;

    @Value("${api.url.transactions.report}")
    private String API_URL_TRANSACTION_REPORT;

    @Test
    void getTransaction_success() {

        // Mock request
        TransactionRequest mockRequest = new TransactionRequest("1067301-1675430916-3");

        // Mock response
        TransactionResponse expectedResponse = TransactionResponse.builder()
                .fx(FxDto.builder()
                        .merchant(FxMerchantDto.builder()
                                .originalAmount(1000L)
                                .originalCurrency("TRY")
                                .build())
                        .build())
                .transaction(TransactionDetailDto.builder()
                        .merchant(TransactionDetailMerchantDto.builder()
                                .referenceNo("549eb867-6e04-4a6c-9776-851332127339")
                                .merchantId(3)
                                .status("ERROR")
                                .channel("API")
                                .agent(TransactionDetailAgentDto.builder()
                                        .id(25416)
                                        .customerIp("84.71.249.228")
                                        .customerUserAgent("okhttp/3.10.0")
                                        .build())
                                .build())
                        .build())
                .customerInfo(CustomerInfoDto.builder()
                        .id(752101L)
                        .created_at(LocalDateTime.of(2023,02,03,13,28,37))
                        .email("a@b.com")
                        .build())
                .merchant(MerchantDto.builder()
                        .name("Dev-Merchant")
                        .build())
                .build();

        ResponseEntity<TransactionResponse> expectedResult = new ResponseEntity<>(expectedResponse, HttpStatus.OK);

        when(authService.getAuthorizationHeader(any())).thenReturn(Optional.of("token"));

        when(restTemplate.exchange(eq(API_URL_TRANSACTION),eq(HttpMethod.POST), any(HttpEntity.class), eq(TransactionResponse.class)))
                .thenReturn(expectedResult);

        TransactionResponse actualResponse = transactionService.getTransaction(mockRequest);

        assertEquals(expectedResponse.getFx(), actualResponse.getFx());
        assertEquals(expectedResponse.getTransaction(), actualResponse.getTransaction());
        assertEquals(expectedResponse.getCustomerInfo(), actualResponse.getCustomerInfo());
        assertEquals(expectedResponse.getMerchant(), actualResponse.getMerchant());
    }

    @Test
    void getTransactionReport_success() {

        LocalDate fromDate = LocalDate.of(2023, 1, 1);
        LocalDate toDate = LocalDate.of(2023, 3, 17);
        TransactionReportRequest mockRequest = new TransactionReportRequest(fromDate, toDate, 1, 1);

        TransactionReportResponse expectedResponse = new TransactionReportResponse(
                "APPROVED",
                null,
                Arrays.asList(
                        new ResponseItemDto("EUR", 10, 100L),
                        new ResponseItemDto("USD",5, 50L)
                )
        );
        ResponseEntity<TransactionReportResponse> expectedResult = new ResponseEntity<>(expectedResponse,HttpStatus.OK);

        when(authService.getAuthorizationHeader(any())).thenReturn(Optional.of("token"));

        when(restTemplate.exchange(eq(API_URL_TRANSACTION_REPORT),eq(HttpMethod.POST), any(HttpEntity.class), eq(TransactionReportResponse.class)))
                .thenReturn(expectedResult);

        TransactionReportResponse actualResponse = transactionService.getTransactionReport(mockRequest);

        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus() );
        assertEquals(expectedResponse.getResponse().size(), actualResponse.getResponse().size());
        assertEquals(expectedResponse.getResponse().get(0), actualResponse.getResponse().get(0));
    }
}