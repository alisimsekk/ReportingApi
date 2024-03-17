package com.alisimsek.ReportingApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.util.Scanner;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (httpResponse.getStatusCode().is5xxServerError() ||
                httpResponse.getStatusCode().is4xxClientError());
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode().is5xxServerError()) {
            if (httpResponse.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                Scanner s = new Scanner(httpResponse.getBody()).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";
                throw new CredentialsNotValid(result);
            }
        } else if (httpResponse.getStatusCode().is4xxClientError()) {
            if (httpResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new TokenExpiredException("Token Expired");
            }
        }
    }
}