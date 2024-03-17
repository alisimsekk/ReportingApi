package com.alisimsek.ReportingApi.dto.response.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDetailMerchantDto {

    private String referenceNo;
    private int merchantId;
    private String status;
    private String channel;
    private String customData;
    private String chainId;
    private String type;
    private int agentInfoId;
    private String operation;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updated_at;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created_at;
    private int id;
    private int fxTransactionId;
    private int acquirerTransactionId;
    private String code;
    private String message;
    private String transactionId;
    private TransactionDetailAgentDto agent;
}
