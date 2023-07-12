package com.example.CurrencyExchange.dto;


//import com.example.CurrencyExchange.model.Friends;
import com.example.CurrencyExchange.model.User;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class UserInput {

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    private String username;
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


    public String getCountry() {
        return country;
    }

    public User toNewUser() {
        User newUser = new User();
        newUser.setUsername(this.username);
        newUser.setLastName(this.lastName);
        newUser.setFirstName(this.firstName);
        newUser.setCountry(this.country);
        newUser.setPassword(this.password);
        newUser.setEmail(this.email);
        return newUser;
    }

}
