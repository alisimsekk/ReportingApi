package com.alisimsek.ReportingApi.controller;

import com.alisimsek.ReportingApi.dto.request.TransactionReportRequest;
import com.alisimsek.ReportingApi.dto.request.TransactionRequest;
import com.alisimsek.ReportingApi.dto.response.transaction.TransactionResponse;
import com.alisimsek.ReportingApi.dto.response.transactionReport.TransactionReportResponse;
import com.alisimsek.ReportingApi.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> getTransaction(@Valid @RequestBody TransactionRequest transactionRequest){
        return ResponseEntity.ok(transactionService.getTransaction(transactionRequest));
    }

    @PostMapping("/report")
    public ResponseEntity<TransactionReportResponse> getTransactionReport(@Valid @RequestBody TransactionReportRequest transactionReportRequest){
        return ResponseEntity.ok(transactionService.getTransactionReport(transactionReportRequest));
    }
}
