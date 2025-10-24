package com.picpay.picpay_challenge.repository;

import com.picpay.picpay_challenge.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
