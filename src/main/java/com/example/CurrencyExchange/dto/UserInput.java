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
    private String password;
    private String country;


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getpassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public User toNewUser() {
        User newUser = new User();

        newUser.setLastName(this.lastName);
        newUser.setFirstName(this.firstName);
        newUser.setCountry(this.country);
        newUser.setpassword(this.password);
        newUser.setEmail(this.email);

        return newUser;
    }

}
