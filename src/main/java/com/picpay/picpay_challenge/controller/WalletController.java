package com.picpay.picpay_challenge.controller;

import com.picpay.picpay_challenge.controller.request.WalletRequest;
import com.picpay.picpay_challenge.controller.response.WalletResponse;
import com.picpay.picpay_challenge.entity.Wallet;
import com.picpay.picpay_challenge.exceptions.UserNotFoundException;
import com.picpay.picpay_challenge.mapper.WalletMapper;
import com.picpay.picpay_challenge.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        return walletService.getWalletByIdUser(id)
                .map(wallet -> ResponseEntity.ok(WalletMapper.toWalletResponse(wallet)))
                .orElseThrow(() -> new UserNotFoundException(id));
    }

}
