package com.picpay.picpay_challenge.controller;

import com.picpay.picpay_challenge.controller.request.TransactionRequest;
import com.picpay.picpay_challenge.controller.response.TransactionResponse;
import com.picpay.picpay_challenge.mapper.TransactionMapper;
import com.picpay.picpay_challenge.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transfer")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> transfer (@RequestBody TransactionRequest transactionRequest){
        transactionService.transfer(TransactionMapper.toTransaction(transactionRequest));

        return ResponseEntity.ok(new TransactionResponse("Transferência realizada com sucesso!"));
    }

}
