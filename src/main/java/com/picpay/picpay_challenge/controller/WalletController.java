package com.picpay.picpay_challenge.controller;

import com.picpay.picpay_challenge.controller.request.WalletRequest;
import com.picpay.picpay_challenge.controller.response.WalletResponse;
import com.picpay.picpay_challenge.entity.Wallet;
import com.picpay.picpay_challenge.mapper.WalletMapper;
import com.picpay.picpay_challenge.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wallet")
@Tag(name = "Wallets", description = "Gerenciamento de carteiras dos usuários")
public class WalletController {

    private final WalletService walletService;

    @Operation(summary = "Cria uma nova carteira")
    @ApiResponse(responseCode = "200", description = "Carteira criada com sucesso")
    @PostMapping
    public ResponseEntity<WalletResponse> saveWallet(@RequestBody WalletRequest walletRequest){
        Wallet saveWallet = walletService.save(WalletMapper.toWallet(walletRequest));

        return ResponseEntity.ok(WalletMapper.toWalletResponse(saveWallet));
    }

    @Operation(summary = "Busca carteira por ID de usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Carteira encontrada"),
            @ApiResponse(responseCode = "404", description = "Carteira não encontrada para o usuário informado")
    })
    @GetMapping("/user/{id}")
    public ResponseEntity<WalletResponse> findByUserId(@PathVariable Long id){
        Wallet existingWallet = walletService.getWalletByIdUser(id);
        return ResponseEntity.ok(WalletMapper.toWalletResponse(existingWallet));
    }

}
