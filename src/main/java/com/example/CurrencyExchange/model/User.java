package com.example.CurrencyExchange.model;

import java.util.ArrayList;
import java.util.List;

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
    private List<Long> friends = new ArrayList<>();

    public void addFriend(Long friendID) {
        this.friends.add(friendID);
    }

    public void removeFriend(Long friendID){
        this.friends.remove(friendID);
    }
    public List<Long> getFriends() {
        return friends;
    }

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

    public Long getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getDOB() {
        return DOB;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getPreferredCurrency() {
        return preferredCurrency;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}
