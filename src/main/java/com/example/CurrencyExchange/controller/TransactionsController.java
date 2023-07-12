package com.example.CurrencyExchange.controller;

import com.example.CurrencyExchange.dto.TransactionInput;
import com.example.CurrencyExchange.model.Transaction;
import com.example.CurrencyExchange.repository.TransactionRepository;
import com.example.CurrencyExchange.service.TransactionServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class TransactionsController {

    private final TransactionServices transactionServices;
    private final TransactionRepository transactionRepository;

    public TransactionsController(TransactionServices transactionService, TransactionRepository transactionRepositor) {
        this.transactionServices = transactionService;
        this.transactionRepository = transactionRepositor;

    }

    // Gets seller by transaction ID
    @GetMapping(path = "/transaction/{ID}/seller")
    public Long getSellerByID(@PathVariable Long ID)
    {
        return transactionRepository.findBySellerID(ID);
    }

    // Gets buyer by transaction ID
    @GetMapping(path = "/transaction/{ID}/buyer")
    public Long getBuyerByID(@PathVariable Long ID)
    {
        return transactionRepository.findByBuyerID(ID);
    }

    // Get all transactions by expiry date
    @GetMapping(path = "transaction/{expdate}/expdate")
    public List<Transaction> getAllbyExpDate(@PathVariable LocalDate expdate)
    {
        return transactionRepository.findAllByExpiryDate(expdate);
    }

    // Add transaction to data base
    @PostMapping(path = "/transaction")
    public void inserTransaction(@RequestBody TransactionInput newTransaction) {
        try {
            transactionServices.insertTransaction(newTransaction);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    // Get all sellers IDs by currency
    @GetMapping(path = "/transaction/findseller/{curfrom}/{curto}")
    public List<Long> getSellresByCurrencies(@PathVariable String curfrom, @PathVariable String curto)
    {
        List<Transaction> temp = transactionRepository.findAllByCurrencyCodeFromAndCurrencyCodeTo(curfrom, curto);
        System.out.println(temp.toString());
        List<Long> sellers = new ArrayList<>();
        for (Transaction transaction: temp)
            if (transaction.isApproved())
                sellers.add(transaction.getSellerID());
        return sellers;
    }

    // Get all buyers IDs by currency
    @GetMapping(path = "/transaction/findbuyer/{curfrom}/{curto}")
    public List<Long> getBuyersByCurrencies(@PathVariable String curfrom, @PathVariable String curto)
    {
        List<Transaction> temp = transactionRepository.findAllByCurrencyCodeFromAndCurrencyCodeTo(curfrom, curto);
        System.out.println(temp.toString());
        List<Long> sellers = new ArrayList<>();
        for (Transaction transaction: temp)
            if (transaction.isApproved())
                sellers.add(transaction.getBuyerID());
        return sellers;
    }

    // Get transaction by ID
    @GetMapping(path = "/transaction/{ID}/id")
    public Transaction findTransactionByID(@PathVariable Long ID)
    {
        return transactionRepository.findByTransactionID(ID);
    }

    // Get all transactions that are finished (approved = true) by sellers ID
    @GetMapping(path = "/transaction/fintx/seller/{ID}")
    public List<Transaction> findFinishedTransactionsBySellersID(@PathVariable Long ID)
    {
        return transactionRepository.findAllBySellerID(ID);
    }


    // Get all transactions that are finished (approved = true) by buyers ID
    @GetMapping(path = "/transaction/fintx/buyer/{ID}")
    public List<Transaction> findFinishedTransactionsByBuyersID(@PathVariable Long ID)
    {
        return transactionRepository.findAllByBuyerID(ID);
    }

    // Set buyer. Needed transaction ID in URL and buyers ID in body of HTTP request
    @PostMapping(path = "/transaction/set/buyer/{ID}")
    public void setBuyer(@PathVariable Long ID, @RequestBody Map<String, Object> request)
    {
        Integer temp = (Integer) request.get("buyerID");
        Long buyerID = Long.valueOf(temp);
        transactionServices.setBuyerByID(ID, buyerID);
    }

    // Set seller. Needed transaction ID in URL and sellers ID in body of HTTP request
    @PostMapping(path = "/transaction/set/seller/{ID}")
    public void setSeller(@PathVariable Long ID, @RequestBody Map<String, Object> request)
    {
        Long sellerID = Long.valueOf((Integer) request.get("sellerID"));
        transactionServices.setSellerByID(ID, sellerID);
    }

    // Get all transactions for a user
    @GetMapping(path = "/transaction/user/{ID}")
    public List<Transaction> getAllTransactionsByUserID(@PathVariable Long ID)
    {
        List<Transaction> result = new ArrayList<>();
        result.addAll(transactionRepository.findAllBySellerID(ID));
        result.addAll(transactionRepository.findAllByBuyerID(ID));
        return result;
    }

    // Get all transactions in DB
    @GetMapping(path = "/transaction/all")
    public List<Transaction> getAllTransactions()
    {
        return transactionRepository.findAll();
    }

}
