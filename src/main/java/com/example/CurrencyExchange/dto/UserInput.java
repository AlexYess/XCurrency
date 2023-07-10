package com.example.CurrencyExchange.dto;


//import com.example.CurrencyExchange.model.Friends;
import com.example.CurrencyExchange.model.User;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class UserInput {
    private String firstName;
    private String lastName;
    private String email;
    private String DOB;
    private String gender;
    private String country;
    private String preferredCurrency;


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

    public User toNewUser() {
        User newUser = new User();

        newUser.setLastName(this.lastName);
        newUser.setFirstName(this.firstName);
        newUser.setCountry(this.country);
        newUser.setDOB(this.DOB);
        newUser.setEmail(this.email);
        newUser.setGender(this.gender);
        newUser.setPreferredCurrency(this.preferredCurrency);

        return newUser;
    }

}
