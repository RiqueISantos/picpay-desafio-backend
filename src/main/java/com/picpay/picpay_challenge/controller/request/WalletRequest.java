package com.picpay.picpay_challenge.controller.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record WalletRequest(BigDecimal balance,
                            Long user) {
}
