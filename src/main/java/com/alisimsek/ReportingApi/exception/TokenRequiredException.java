package com.alisimsek.ReportingApi.exception;

public class TokenRequiredException extends RuntimeException{

    public TokenRequiredException(String message) {
        super(message);
    }
}