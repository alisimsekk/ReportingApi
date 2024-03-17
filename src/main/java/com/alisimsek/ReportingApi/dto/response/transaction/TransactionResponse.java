package com.alisimsek.ReportingApi.dto.response.transaction;

import com.alisimsek.ReportingApi.dto.response.client.CustomerInfoDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class TransactionResponse {

    private String status;
    private String message;
    private FxDto fx;
    private TransactionDetailDto transaction;
    private CustomerInfoDto customerInfo;
    private MerchantDto merchant;
}
