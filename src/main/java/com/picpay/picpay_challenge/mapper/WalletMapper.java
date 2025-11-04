package com.picpay.picpay_challenge.mapper;

import com.picpay.picpay_challenge.controller.request.WalletRequest;
import com.picpay.picpay_challenge.controller.response.WalletResponse;
import com.picpay.picpay_challenge.entity.User;
import com.picpay.picpay_challenge.entity.Wallet;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WalletMapper {

    public static Wallet toWallet(WalletRequest walletRequest){

        User userId = User.builder()
                .id(walletRequest.user())
                .build();

        return Wallet
                .builder()
                .balance(walletRequest.balance())
                .user(userId)
                .build();
    }

    public static WalletResponse toWalletResponse(Wallet wallet){

        return WalletResponse
                .builder()
                .id(wallet.getId())
                .balance(wallet.getBalance())
                .user(wallet.getUser().getId())
                .build();
    }
}
