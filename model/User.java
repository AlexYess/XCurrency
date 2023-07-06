package model;

import java.util.ArrayList;
import java.util.List;
import model.Transaction;

public class User {
    private Long userID;
    private String firstName;
    private String lastName;
    private String email;
    private String DOB;
    private String gender;
    private String country;
    private String preferredCurrency;
    private List<Transaction> transactionHistory = new ArrayList<>();

    public User(Long userID, String firstName, String lastName, String email, String DOB, String gender, String country, String preferredCurrency) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.DOB = DOB;
        this.gender = gender;
        this.country = country;
        this.preferredCurrency = preferredCurrency;
    }
}
