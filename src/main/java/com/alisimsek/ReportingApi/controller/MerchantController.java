package com.alisimsek.ReportingApi.controller;

import com.alisimsek.ReportingApi.dto.request.UserLoginRequest;
import com.alisimsek.ReportingApi.dto.response.UserResponse;
import com.alisimsek.ReportingApi.service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/merchants")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService reportingService;

    @PostMapping("/user/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody UserLoginRequest userLoginRequest){
        return ResponseEntity.ok(reportingService.login(userLoginRequest));
    }
}
