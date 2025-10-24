package com.picpay.picpay_challenge.controller.request;

import com.picpay.picpay_challenge.enums.TypeUser;
import lombok.Builder;

@Builder
public record UserRequest(String fullName,
                          String cpf,
                          String email,
                          String password,
                          TypeUser typeUser) {
}
