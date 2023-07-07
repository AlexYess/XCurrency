package com.example.CurrencyExchange.controller;

import com.example.CurrencyExchange.model.Transaction;
import com.example.CurrencyExchange.service.TransactionServices;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class TransactionsController {

    private TransactionServices transactionServices;

    @PostConstruct
    public void init()
    {
        transactionServices.transactionExpiry();
    }

    public TransactionsController(TransactionServices transactionServices) {
        this.transactionServices = transactionServices;
    }

//    @GetMapping(path = "/transactions")
////    public List<Transaction> GetTransactionsHistory() {
////        return transactionServices.getTransactionsHistory();
////    }

    @PostMapping(path = "/transactions")
    public void insertTransaction(Transaction newTransaction) {
        try {
            transactionServices.insertTransaction(newTransaction);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping(path = "/transaction/{ID}")
    public Optional<Transaction> GetTransactionId(@PathVariable("ID") Long transactionID) {
        if (transactionServices.getTransactionByID(transactionID).isEmpty()) {
            throw new IllegalArgumentException("No transaction found");
        }
        return Optional.of(transactionServices.getTransactionByID(transactionID).get());
    }

    @PostMapping(path = "/transaction/{ID}/confirmation")
    public void approveTransaction(@PathVariable("ID") Long confimationID, boolean confirmation)
    {
        try {
            transactionServices.approveTransactionByID(confimationID, confirmation);
        }
        catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping(path = "/transaction/{ID}/expdate")
    public void changeExpiryDate(@PathVariable("ID") Long expdateID, String date)
    {
        try {
            transactionServices.changeExpiryDateByID(expdateID, date); // date format YYYY-MM-DD
        }
        catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping(path = "/transaction/{ID}/clients/buyer")
    public Optional<Long> getBuyer(@PathVariable("ID") Long buyerID)
    {
        if (transactionServices.getTransactionByID(buyerID).isEmpty()) {
            throw new IllegalArgumentException("No transaction found");
        }
        return Optional.of(transactionServices.getBuyerByID(buyerID).get());
    }

    @GetMapping(path = "/transaction/{ID}/clients/seller")
    public Optional<Long> getSeller(@PathVariable("ID") Long sellerID)
    {
        if (transactionServices.getTransactionByID(sellerID).isEmpty()) {
            throw new IllegalArgumentException("No transaction found");
        }
        return Optional.of(transactionServices.getSellerByID(sellerID).get());
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void runDailyCheck()
    {
        transactionServices.transactionExpiry();
    }
}
