package com.alisimsek.ReportingApi.dto.response.transactionReport;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionReportResponse {

    private String status;
    private String message;
    private List<ResponseItemDto> response;
}
