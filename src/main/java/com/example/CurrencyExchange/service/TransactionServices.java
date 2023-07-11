//  insert transaction -- Done
// find transaction by ID -- Done
// return all transactions -- in work


package com.example.CurrencyExchange.service;

import com.example.CurrencyExchange.dto.TransactionInput;
import com.example.CurrencyExchange.model.Transaction;
import com.example.CurrencyExchange.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServices {
    private TransactionRepository transactionRepository;

    public TransactionServices(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction insertTransaction(TransactionInput transactionInput)
    {
        Transaction newTransaction = transactionInput.toNewTransaction();
        transactionRepository.save(newTransaction);
        return newTransaction;
    }
}
