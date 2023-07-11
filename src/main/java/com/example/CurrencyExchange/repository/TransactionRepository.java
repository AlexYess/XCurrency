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

    //find seller by 2 codes and if approved is false
    //find buyer by code (simmilar to the previous one)
    //find finished transactions by seller
    //find finished transactions by buyer
    //set sellers id
    //set buyer id
    //all transactions by user id
    //delete a transaction but keep in history
    //



}
