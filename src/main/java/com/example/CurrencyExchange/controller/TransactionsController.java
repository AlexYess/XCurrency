package com.example.CurrencyExchange.controller;

import com.example.CurrencyExchange.dto.TransactionInput;
import com.example.CurrencyExchange.dto.UserInput;
import com.example.CurrencyExchange.model.Transaction;
import com.example.CurrencyExchange.repository.TransactionRepository;
import com.example.CurrencyExchange.service.TransactionServices;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
public class TransactionsController {

    private TransactionServices transactionServices;
    private TransactionRepository transactionRepository;

//    @PostConstruct
//    public void init()
//    {
////        transactionServices.transactionExpiry();
////        transactionServices.testingShit();
//    }

    public TransactionsController(TransactionServices transactionService, TransactionRepository transactionRepositor) {
        this.transactionServices = transactionService;
        this.transactionRepository = transactionRepositor;

    }


//    @GetMapping(path = "/transaction/{ID}")
//    public Optional<Transaction> GetTransactionId(@PathVariable("ID") Long transactionID) {
//        return transactionServices.getTransactionByID(transactionID);
//    }

//    @PostMapping(path = "/transaction/{transactionId}/confirmation")
//    public void confirmTransaction(@PathVariable("transactionId") Long transactionId, @RequestBody Map<String, Object> request) {
//        boolean isApproved = (boolean) request.get("isApproved"); // Получаем значение isApproved из запроса
//
//        // Выполняем обновление значения в базе данных
//        transactionServices.approveTransactionByID(transactionId, isApproved);
//    }


//    @PostMapping(path = "/transaction/{ID}/expdate")
//    public void changeExpiryDate(@PathVariable("ID") Long expdateID, String date)
//    {
//        try {
//            transactionServices.changeExpiryDateByID(expdateID, date); // date format YYYY-MM-DD
//        }
//        catch (IllegalArgumentException e) {
//            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//    }
//
//    @GetMapping(path = "/transaction/{ID}/clients/buyer")
//    public Optional<Long> getBuyer(@PathVariable("ID") Long ID)
//    {
//        if (transactionServices.getTransactionByID(ID).isEmpty()) {
//            throw new IllegalArgumentException("No transaction found");
//        }
////        updateCurrencyRate(ID);
//        return Optional.of(transactionServices.getBuyerByID(ID).get());
//    }

//    @GetMapping(path = "/transaction/{ID}/clients/seller")
//    public Optional<Long> getSeller(@PathVariable("ID") Long sellerID)
//    {
//        return transactionServices.getSellerByID(sellerID);
//    }


//    @Scheduled(cron = "0 0 0 * * *")
//    public void runDailyCheck()
//    {
//        transactionServices.transactionExpiry();
//    }
//
//    public void updateCurrencyRate(Long ID)
//    {
//        transactionServices.rateUpdaterById(ID);
//    }
//
//    @Scheduled(cron = "0 0 */8 * * *")
//    public void updateCurrencyRateAll()
//    {
//        transactionServices.rateUpdaterAll();
//    }

//    @PostMapping(path = "/transactions")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Transaction addTransaction(@RequestBody TransactionInput transaction) {
//        Transaction newTransaction = transaction.toNewTransaction();
//        transactionRepository.save(newTransaction);
//        return newTransaction;
//    }
//
//    @GetMapping(path = "/transactions/approved")
//    public List<Transaction> getApprovedTransactions()
//    {
//        return transactionRepository.findAllByisApproved(true);
//    }

    @GetMapping(path = "/transaction/{ID}/seller")
    public Long getSellerByID(@PathVariable Long ID)
    {
        return transactionRepository.findBySellerID(ID);
    }

    @GetMapping(path = "/transaction/{ID}/buyer")
    public Long getBuyerByID(@PathVariable Long ID)
    {
        return transactionRepository.findByBuyerID(ID);
    }

    @GetMapping(path = "transaction/{expdate}/expdate")
    public List<Transaction> getAllbyExpDate(@PathVariable LocalDate expdate)
    {
        return transactionRepository.findAllByExpiryDate(expdate);
    }

    @PostMapping(path = "/transaction")
    public void insertUser(@RequestBody TransactionInput newTransaction) {
        try {
            transactionServices.insertTransaction(newTransaction);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping(path = "/transaction/{ID}/id")
    public Transaction findTransactionByID(@PathVariable Long ID)
    {
        return transactionRepository.findByTransactionID(ID);
    }

}
