package com.example.CurrencyExchange.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long userID;
    private Long friendID;
    private String date;


    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public void setFriendID(Long friendID) {
        this.friendID = friendID;
    }


    public Friend() {

    }
}
