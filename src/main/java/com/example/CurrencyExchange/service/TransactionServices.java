//  insert transaction -- Done
// find transaction by ID -- Done
// return all transactions -- in work


package com.example.CurrencyExchange.service;

import com.example.CurrencyExchange.dto.TransactionInput;
import com.example.CurrencyExchange.model.Transaction;
import com.example.CurrencyExchange.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class TransactionServices {
    private TransactionRepository transactionRepository;

    public TransactionServices(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction insertTransaction(TransactionInput transactionInput) {
        Transaction newTransaction = transactionInput.toNewTransaction();
        newTransaction.setRate();
        transactionRepository.save(newTransaction);
        return newTransaction;
    }

    public void setBuyerByID(Long ID, Long buyerID)
    { // ПОМЕНЯЙ ПУТЬ ДО БАЗЫ БАННЫХ
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/my_db", "my_db_user", "passw0rd")) {
            String updateQuery = "UPDATE TRANSACTION SET BUYERID = ? WHERE TRANSACTIONID = ?";

            updaterBuyerSellerID(ID, buyerID, connection, updateQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setSellerByID(Long ID, Long sellerID)
    {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/my_db", "my_db_user", "passw0rd")) {
            String updateQuery = "UPDATE TRANSACTION SET SELLERID = ? WHERE TRANSACTIONID = ?";

            updaterBuyerSellerID(ID, sellerID, connection, updateQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updaterBuyerSellerID(Long ID, Long sellerID, Connection connection, String updateQuery) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setLong(1, sellerID);
            statement.setLong(2, ID);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data updated successfully.");
            } else {
                System.out.println("No rows were updated.");
            }
        }
    }
}
