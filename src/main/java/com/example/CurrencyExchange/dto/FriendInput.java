package com.example.CurrencyExchange.dto;

import com.example.CurrencyExchange.model.Friend;
import com.example.CurrencyExchange.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


public class FriendInput {

    private Long id;
    private Long userID;
    private Long friendID;

    public Long getId() {
        return id;
    }

    public Long getUserID() {
        return userID;
    }

    public Long getFriendID() {
        return friendID;
    }

//    public String getDate() {
//        return date;
//    }

//    private String date;
//    public Friend toNewFriend() {
//        Friend newFriend = new Friend();
//        newFriend.setFriendID(this.friendID);
//        newFriend.setUser(this.user);
//        newFriend.setDate(this.date);
//        return newFriend;
//    }
public Friend toNewFriend() {
    Friend newFriend = new Friend();
    newFriend.setUserID(this.userID);
    newFriend.setFriendID(this.friendID);
    return newFriend;
}

}
