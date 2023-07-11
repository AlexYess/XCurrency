package com.example.CurrencyExchange.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private String country;
    private String preferredCurrency;
//    @OneToMany(mappedBy = "user")
//    @JsonManagedReference
//    private List<Friend> friends = new ArrayList<>();
    public User() {

    }


    public User(Long userID, String firstName, String lastName, String email, String password, String gender, String country, String preferredCurrency) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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

    public String getpassword() {
        return password;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPreferredCurrency(String preferredCurrency) {
        this.preferredCurrency = preferredCurrency;
    }


}
