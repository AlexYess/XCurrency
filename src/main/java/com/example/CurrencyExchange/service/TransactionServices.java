//  insert transaction -- Done
// find transaction by ID -- Done
// return all transactions -- in work


package com.example.CurrencyExchange.service;

import com.example.CurrencyExchange.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServices {
    private List<Transaction> transactionList = new ArrayList<>();

    public Optional<Transaction> GetTransactionByID(Long ID)
    {
        for (Transaction transaction: transactionList)
            if (transaction.getTransactionID().equals(ID))
                return Optional.of(transaction);
        return Optional.empty();
    }

    public void insertTransaction(Transaction newTransaction)
    {
        transactionList.add(newTransaction);
    }

    public List<Transaction> getTransactionsHistory()
    {
        if (!transactionList.isEmpty())
            return transactionList;
        return null;
    }



}
