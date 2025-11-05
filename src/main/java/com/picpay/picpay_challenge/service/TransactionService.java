package com.picpay.picpay_challenge.service;

import com.picpay.picpay_challenge.client.AuthorizerClient;
import com.picpay.picpay_challenge.client.NotificationClient;
import com.picpay.picpay_challenge.entity.Transaction;
import com.picpay.picpay_challenge.entity.User;
import com.picpay.picpay_challenge.entity.Wallet;
import com.picpay.picpay_challenge.enums.TypeUser;
import com.picpay.picpay_challenge.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private final UserService userService;
    private final WalletService walletService;
    private final TransactionRepository transactionRepository;
    private final AuthorizerClient authorizerClient;
    private final NotificationClient notificationClient;

    @Transactional
    public void transfer(Transaction transaction){

        User sender = userService.findUserById(transaction.getSender().getId());
        User receiver = userService.findUserById(transaction.getReceiver().getId());

        if(sender.getTypeUser() == TypeUser.ADMIN){
            throw new IllegalArgumentException("Lojistas não podem enviar transferências.");
        }

        Wallet senderWallet = walletService.getWalletByIdUser(sender.getId());
        Wallet receiverWallet = walletService.getWalletByIdUser(receiver.getId());

        if(senderWallet.getBalance().compareTo(transaction.getAmount()) < 0){
            throw new IllegalArgumentException("Saldo insuficiente para realizar a transferência.");
        }

        if (!authorizerClient.isAuthorized()) {
            throw new IllegalArgumentException("Transação não autorizada pelo serviço externo.");
        }

        senderWallet.setBalance(senderWallet.getBalance().subtract(transaction.getAmount()));
        receiverWallet.setBalance(receiverWallet.getBalance().add(transaction.getAmount()));

        walletService.save(senderWallet);
        walletService.save(receiverWallet);

        Transaction saved = transactionRepository.save(transaction);

        notificationClient.notifyUser();

        try {
            notificationClient.notifyUser();
        } catch (Exception e) {
            System.out.println("⚠️ Falha ao enviar notificação: " + e.getMessage());
        }
    }


}
