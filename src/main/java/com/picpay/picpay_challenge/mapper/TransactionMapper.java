package com.picpay.picpay_challenge.mapper;

import com.picpay.picpay_challenge.controller.request.TransactionRequest;
import com.picpay.picpay_challenge.entity.Transaction;
import com.picpay.picpay_challenge.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransactionMapper {

    public static Transaction toTransaction(TransactionRequest transactionRequest){

        User sender = User.builder()
                .id(transactionRequest.sender())
                .build();

        User receiver = User.builder()
                .id(transactionRequest.receiver())
                .build();

        return Transaction
                .builder()
                .sender(sender)
                .receiver(receiver)
                .amount(transactionRequest.amount())
                .build();
    }

}
