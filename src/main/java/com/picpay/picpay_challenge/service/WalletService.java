package com.picpay.picpay_challenge.service;

import com.picpay.picpay_challenge.entity.User;
import com.picpay.picpay_challenge.entity.Wallet;
import com.picpay.picpay_challenge.exceptions.UserNotFoundException;
import com.picpay.picpay_challenge.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserService userService;

    public Wallet save(Wallet wallet){
        User user = userService.findUserById(wallet.getUser().getId())
                .orElseThrow(() -> new UserNotFoundException(wallet.getUser().getId()));

        wallet.setUser(user);

        return walletRepository.save(wallet);
    }

    public Optional<Wallet> getWalletByIdUser(Long idUser){
        userService.findUserById(idUser);
        return walletRepository.findByUserId(idUser);
    }

}
