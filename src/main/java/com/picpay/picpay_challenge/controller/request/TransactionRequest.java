package com.picpay.picpay_challenge.controller.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransactionRequest(Long sender,
                                 Long receiver,
                                 BigDecimal amount) {
}
