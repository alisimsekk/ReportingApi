package com.alisimsek.ReportingApi.dto.response.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetailDto {

    private TransactionDetailMerchantDto merchant;
}
