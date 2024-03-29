package com.alisimsek.ReportingApi.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionReportRequest {

    @NotNull (message = "FromDate can't be empty")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @NotNull (message = "ToDate can't be empty")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    private Integer merchant;
    private Integer acquirer;
}
