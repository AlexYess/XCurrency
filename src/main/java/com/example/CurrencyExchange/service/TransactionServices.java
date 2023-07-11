//  insert transaction -- Done
// find transaction by ID -- Done
// return all transactions -- in work


package com.example.CurrencyExchange.service;

import com.example.CurrencyExchange.model.Transaction;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.Optional;

@Service
public class TransactionServices {

    public Optional<Transaction> getTransactionByID(Long ID)
    {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/my_db", "my_db_user", "passw0rd");
            String sql = "SELECT * FROM TRANSACTION WHERE TRANSACTIONID = " + ID.toString();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            Transaction transaction = new Transaction(
                    resultSet.getLong("TRANSACTIONID"),
                    resultSet.getLong("SELLERID"),
                    resultSet.getLong("BUYERID"),
                    resultSet.getString("CURRENCY_CODE_FROM"),
                    resultSet.getString("CURRENCY_CODE_TO"),
                    resultSet.getFloat("RATE"),
                    resultSet.getFloat("AMOUNT"),
                    resultSet.getDate("EXPIRY_DATE").toLocalDate(),
                    resultSet.getBoolean("IS_APPROVED")
                    );
            return Optional.of(transaction);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public Optional<Long> getSellerByID(Long ID)
    {
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:file:./main_db", "sa", "password");
            String sql = "SELECT SELLERID FROM TRANSACTION WHERE TRANSACTIONID = " + ID.toString();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println(resultSet);
            resultSet.next();
            return Optional.of(resultSet.getLong("SELLERID"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Optional<Long> getBuyerByID(Long ID)
    {
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:file:./main_db", "sa", "password");
            String sql = "SELECT BUYERID FROM TRANSACTION WHERE TRANSACTIONID = " + ID.toString();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println(resultSet);
            resultSet.next();
            return Optional.of(resultSet.getLong("BUYERID"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void approveTransactionByID(Long ID, boolean approvement)
    {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:file:./main_db", "sa", "password")) {
            String updateQuery = "UPDATE TRANSACTION SET IS_APPROVED = ? WHERE TRANSACTIONID = ?";

            try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                statement.setBoolean(1, approvement);
                statement.setLong(2, ID);
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data updated successfully.");
                } else {
                    System.out.println("No rows were updated.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void changeExpiryDateByID(Long ID, String exp_date)
//    {
//        for (Transaction transaction: transactionList)
//            if (transaction.getTransactionID().equals(ID))
//            {
//                transaction.setExpiryDate(LocalDate.parse(exp_date));
//                break;
//            }
//    }

//    public void transactionExpiry()
//    {
//        LocalDate current = LocalDate.now();
//        for (Transaction transaction: transactionList)
//        {
//            if (transaction.getExpiryDate().isBefore(current.minusWeeks(2)))
//            {
//                transaction.setApproved(false);
//            }
//        }
//
//    }

//    public void rateUpdaterById(Long ID)
//    {
//        for (Transaction transaction: transactionList)
//            if (transaction.getTransactionID().equals(ID))
//            {
//                transaction.setRate();
//            }
//    }

//    public void rateUpdaterAll()
//    {
//        for (Transaction transaction: transactionList)
//        {
//            transaction.setRate();
//        }
//    }

}
