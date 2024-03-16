package com.alisimsek.ReportingApi.dto.response.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfoResponse {

    private String billingFirstName;
    private String billingLastName;
    private String email;
    private String billingCompany;
    private String billingCity;
    private String updated_at;
    private String created_at;
    private Long id;
    private String billingAddress1;
}
