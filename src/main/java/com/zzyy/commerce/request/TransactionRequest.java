package com.zzyy.commerce.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {

    private Long userId;
    private BigDecimal amount;
    private Long refundId;
}
