package com.alisimsek.ReportingApi.dto.response.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FxMerchantDto {

    private Long originalAmount;
    private String originalCurrency;
}
