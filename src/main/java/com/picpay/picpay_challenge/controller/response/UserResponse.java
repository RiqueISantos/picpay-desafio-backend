package com.picpay.picpay_challenge.controller.response;

import com.picpay.picpay_challenge.enums.TypeUser;
import lombok.Builder;

@Builder
public record UserResponse(String fullName,
                           String email,
                           TypeUser typeUser) {
}
