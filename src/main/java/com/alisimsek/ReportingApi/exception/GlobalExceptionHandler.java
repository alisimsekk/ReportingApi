package com.alisimsek.ReportingApi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CredentialsNotValid.class)
    public ResponseEntity<?> credentialsNotValidExceptionHandler(CredentialsNotValid exception){
        log.error(exception.toString(),exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<?> tokenExpiredExceptionHandler(TokenExpiredException exception){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(0)
                .status("DECLINED")
                .message(exception.getMessage())
                .build();
        log.error(errorResponse.toString(), exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenRequiredException.class)
    public ResponseEntity<?> tokenRequiredExceptionHandler(TokenRequiredException exception){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status("DECLINED")
                .message(exception.getMessage())
                .build();
        log.error(exception.toString(),exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
