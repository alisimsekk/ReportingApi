package com.alisimsek.ReportingApi.dto.response.transactionReport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionReportResponse {

    private String status;
    private List<ResponseItemDto> response;
}
