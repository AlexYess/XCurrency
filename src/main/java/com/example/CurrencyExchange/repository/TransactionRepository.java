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

    List<Transaction> findAllByCurrencyCodeFromAndCurrencyCodeTo(String CurrencyFrom, String CurrencyTo);

    List<Transaction> findAllBySellerID(Long ID);

    List<Transaction> findAllByBuyerID(Long ID);






    //find seller by 2 codes and if approved is false - d
    //find buyer by code (similar to the previous one) - d
    //find finished transactions by seller - d
    //find finished transactions by buyer - d
    //set sellers id - d
    //set buyer id - d
    //all transactions by user id - d
    //delete a transaction but keep in history -
    //update currency rate - half done. rate updates only on creation or updated of each transaction



}
