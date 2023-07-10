//  insert transaction -- Done
// find transaction by ID -- Done
// return all transactions -- in work


package com.example.CurrencyExchange.service;

import com.example.CurrencyExchange.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServices {
    private List<Transaction> transactionList = new ArrayList<>();

    public Optional<Transaction> getTransactionByID(Long ID)
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

//    public List<Transaction> getTransactionsHistory()
//    {
//        if (!transactionList.isEmpty())
//            return transactionList;
//        return null;
//    }

    public Optional<Long> getSellerByID(Long ID)
    {
        for (Transaction transaction: transactionList)
        {
            if (transaction.getTransactionID().equals(ID))
                return Optional.of(transaction.getSellerID());
        }
        return Optional.empty();
    }


    public Optional<Long> getBuyerByID(Long ID)
    {
        for (Transaction transaction: transactionList)
        {
            if (transaction.getTransactionID().equals(ID))
                return Optional.of(transaction.getBuyerID());
        }
        return Optional.empty();
    }

    public void approveTransactionByID(Long ID, boolean approvement)
    {
        for (Transaction transaction: transactionList)
            if (transaction.getTransactionID().equals(ID))
            {
                transaction.setApproved(approvement);
                break;
            }
    }

    public void changeExpiryDateByID(Long ID, String exp_date)
    {
        for (Transaction transaction: transactionList)
            if (transaction.getTransactionID().equals(ID))
            {
                transaction.setExpiryDate(LocalDate.parse(exp_date));
                break;
            }
    }

    // set approvment status to false if past exp date

    public void transactionExpiry()
    {
        LocalDate current = LocalDate.now();
        for (Transaction transaction: transactionList)
        {
            if (transaction.getExpiryDate().isBefore(current.minusWeeks(2)))
            {
                transaction.setApproved(false);
            }
        }

    }

    public void rateUpdaterById(Long ID)
    {
        for (Transaction transaction: transactionList)
            if (transaction.getTransactionID().equals(ID))
            {
                transaction.setRate();
            }
    }

    public void rateUpdaterAll()
    {
        for (Transaction transaction: transactionList)
        {
            transaction.setRate();
        }
    }


    // add regural expression to check if date is correct

    // buy/sell currency. Modify the insert-transaction

    // delete transaction after some time?
}
