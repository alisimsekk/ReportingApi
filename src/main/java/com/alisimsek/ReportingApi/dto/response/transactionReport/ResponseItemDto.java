package com.alisimsek.ReportingApi.dto.response.transactionReport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseItemDto {

    private int count;
    private Long total;
    private String currency;
}
