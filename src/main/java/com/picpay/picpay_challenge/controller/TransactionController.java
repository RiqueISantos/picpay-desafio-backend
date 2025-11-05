package com.picpay.picpay_challenge.controller;

import com.picpay.picpay_challenge.controller.request.TransactionRequest;
import com.picpay.picpay_challenge.controller.response.TransactionResponse;
import com.picpay.picpay_challenge.mapper.TransactionMapper;
import com.picpay.picpay_challenge.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transfer")
@Tag(name = "Transactions", description = "Operações relacionadas a transferências entre usuários")
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "Realiza uma transferência entre usuários")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transferência realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos na solicitação")
    })
    @PostMapping
    public ResponseEntity<TransactionResponse> transfer (@RequestBody TransactionRequest transactionRequest){
        transactionService.transfer(TransactionMapper.toTransaction(transactionRequest));

        return ResponseEntity.ok(new TransactionResponse("Transferência realizada com sucesso!"));
    }

}
