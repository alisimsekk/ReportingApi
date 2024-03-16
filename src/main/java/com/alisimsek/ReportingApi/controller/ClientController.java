package com.alisimsek.ReportingApi.controller;

import com.alisimsek.ReportingApi.dto.request.TransactionRequest;
import com.alisimsek.ReportingApi.dto.response.client.ClientResponse;
import com.alisimsek.ReportingApi.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponse> getClient (@Valid @RequestBody TransactionRequest transactionRequest){
        return ResponseEntity.ok(clientService.getClient(transactionRequest));
    }
}
