package com.example.CurrencyExchange.repository;

import com.example.CurrencyExchange.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    Transaction findByTransactionID(Long ID);

    Long findBySellerID(Long ID);

    Long findByBuyerID(Long ID);

    List<Transaction> findAllByExpiryDate(LocalDate expDate);



}
