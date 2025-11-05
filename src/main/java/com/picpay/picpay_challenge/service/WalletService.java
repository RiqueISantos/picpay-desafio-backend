package com.picpay.picpay_challenge.service;

import com.picpay.picpay_challenge.entity.User;
import com.picpay.picpay_challenge.entity.Wallet;
import com.picpay.picpay_challenge.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserService userService;

    @CacheEvict(value = "wallets", key = "#wallet.user.id")
    public Wallet save(Wallet wallet){
        User user = userService.findUserById(wallet.getUser().getId());
        wallet.setUser(user);

        return walletRepository.save(wallet);
    }

    @Cacheable(value = "wallets", key = "#idUser")
    public Wallet getWalletByIdUser(Long idUser){
        userService.findUserById(idUser);
        return walletRepository.findByUserId(idUser).orElseThrow(() -> new IllegalArgumentException("Carteira não encontrada para o usuário"));
    }

}
