package com.example.CurrencyExchange.controller;

import com.example.CurrencyExchange.dto.CurrencyInput;
import com.example.CurrencyExchange.dto.TransactionInput;
import com.example.CurrencyExchange.model.CurrencyObject;
import com.example.CurrencyExchange.model.Transaction;
import com.example.CurrencyExchange.repository.TransactionRepository;
import com.example.CurrencyExchange.service.TransactionServices;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@RestController
public class TransactionsController {

    private TransactionServices transactionServices;
    private TransactionRepository transactionRepository;

    @PostConstruct
    public void init()
    {
        transactionServices.transactionExpiry();
    }

    public TransactionsController(TransactionServices transactionServices, TransactionRepository transactionRepository) {
        this.transactionServices = transactionServices;
        this.transactionRepository = transactionRepository;

    }

//    @GetMapping(path = "/transactions")
//    public List<Transaction> GetTransactionsHistory() {
//        return transactionServices.getTransactionsHistory();
//    }

//    @PostMapping(path = "/transactions")
//    public void insertTransaction(@RequestBody Transaction newTransaction) {
//        try {
//            transactionServices.insertTransaction(newTransaction);
//        } catch (IllegalArgumentException e) {
//            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//    }

    @GetMapping(path = "/transaction/{ID}")
    public Optional<Transaction> GetTransactionId(@PathVariable("ID") Long transactionID) {
        if (transactionServices.getTransactionByID(transactionID).isEmpty()) {
            throw new IllegalArgumentException("No transaction found");
        }
        updateCurrencyRate(transactionID);
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
    public Optional<Long> getBuyer(@PathVariable("ID") Long ID)
    {
        if (transactionServices.getTransactionByID(ID).isEmpty()) {
            throw new IllegalArgumentException("No transaction found");
        }
        updateCurrencyRate(ID);
        return Optional.of(transactionServices.getBuyerByID(ID).get());
    }

    @GetMapping(path = "/transaction/{ID}/clients/seller")
    public Optional<Long> getSeller(@PathVariable("ID") Long sellerID)
    {
        if (transactionServices.getTransactionByID(sellerID).isEmpty()) {
            throw new IllegalArgumentException("No transaction found");
        }
        updateCurrencyRate(sellerID);
        return Optional.of(transactionServices.getSellerByID(sellerID).get());
    }


    @Scheduled(cron = "0 0 0 * * *")
    public void runDailyCheck()
    {
        transactionServices.transactionExpiry();
    }

    public void updateCurrencyRate(Long ID)
    {
        transactionServices.rateUpdaterById(ID);
    }

    @Scheduled(cron = "0 0 */8 * * *")
    public void updateCurrencyRateAll()
    {
        transactionServices.rateUpdaterAll();
    }

    @PostMapping(path = "/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction addTransaction(@RequestBody TransactionInput transaction) {
        Transaction newTransaction = transaction.toNewTransaction();
        transactionRepository.save(newTransaction);
        try {
            transactionServices.insertTransaction(newTransaction);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return newTransaction;
    }

}
