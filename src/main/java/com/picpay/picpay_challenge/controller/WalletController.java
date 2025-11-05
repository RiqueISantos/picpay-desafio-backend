package com.picpay.picpay_challenge.controller;

import com.picpay.picpay_challenge.controller.request.WalletRequest;
import com.picpay.picpay_challenge.controller.response.WalletResponse;
import com.picpay.picpay_challenge.entity.Wallet;
import com.picpay.picpay_challenge.mapper.WalletMapper;
import com.picpay.picpay_challenge.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<WalletResponse> saveWallet(@RequestBody WalletRequest walletRequest){
        Wallet saveWallet = walletService.save(WalletMapper.toWallet(walletRequest));

        return ResponseEntity.ok(WalletMapper.toWalletResponse(saveWallet));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<WalletResponse> findByUserId(@PathVariable Long id){
        Wallet existingWallet = walletService.getWalletByIdUser(id);
        return ResponseEntity.ok(WalletMapper.toWalletResponse(existingWallet));
    }

}
