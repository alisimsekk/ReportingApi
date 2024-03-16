package com.alisimsek.ReportingApi.dto.response.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientResponse {

    private String status;
    private String message;
    private CustomerInfoResponse customerInfo;
}
