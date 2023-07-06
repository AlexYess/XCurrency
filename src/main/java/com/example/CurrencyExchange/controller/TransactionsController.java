package com.example.CurrencyExchange.controller;

import com.example.CurrencyExchange.model.Transaction;
import com.example.CurrencyExchange.service.TransactionServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class TransactionsController {

    private TransactionServices transactionServices;
    public TransactionsController(TransactionServices transactionServices) {
        this.transactionServices = transactionServices;
    }
    @GetMapping(path = "/transactions")
    public List<Transaction> GetTransactionsHistory() {
        return transactionServices.getTransactionsHistory();
    }
    @PostMapping(path = "/transactions")
    public void insertTransaction(Transaction newTransaction) {
        try {
            transactionServices.insertTransaction(newTransaction);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    @GetMapping(path = "/currencies/{transactionID}")
    public Optional<Transaction> GetTransactionId(Long ID) {
        if (transactionServices.getTransactionByID(ID).isPresent()) {
            throw new IllegalArgumentException("Currency already present");
        }
        return Optional.of(transactionServices.getTransactionByID(ID).get());
    }
}
