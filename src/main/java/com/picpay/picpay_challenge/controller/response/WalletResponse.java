package com.picpay.picpay_challenge.controller.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record WalletResponse(Long id,
                             BigDecimal balance,
                             Long user) {
}
