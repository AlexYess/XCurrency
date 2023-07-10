package com.example.CurrencyExchange.repository;

import com.example.CurrencyExchange.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> FindByUserID(Long ID);
}
