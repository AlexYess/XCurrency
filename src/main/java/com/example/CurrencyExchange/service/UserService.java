package com.example.CurrencyExchange.service;

import com.example.CurrencyExchange.model.Transaction;
import com.example.CurrencyExchange.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();
//    private List<Long> userIDs = new ArrayList<>();
    public List<User> getAllUsers() {
        return users;
    }

    public void insertUser(User newUser) {
        Optional<User> userWithGivenId = getUser(newUser.getUserID());
        if(userWithGivenId.isPresent()) {
            throw new IllegalArgumentException("Id already present");
        }
        users.add(newUser);
    }

    public Optional<User> getUser(Long userID) {
        for (User user : users) {
            if (userID.equals(user.getUserID())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public void editUser(Long existingUserId, User newUser){
        if(!newUser.getUserID().equals(existingUserId)){
            throw new IllegalArgumentException("Id does not match");
        }

        for (User user : users) {
            if (existingUserId.equals(user.getUserID())) {
                int index = users.indexOf(user);
                users.set(index, newUser);
                return;
            }
        }
        throw new IllegalArgumentException("Id does not exist");
    }

    public void deleteUser(Long existingUserId){
        for (User user : users) {
            if (existingUserId.equals(user.getUserID())) {
                users.remove(user);
                return;
            }
        }
    }

    public List<Transaction> getTxHistory(Long userID){
        for (User user : users) {
            if (userID.equals(user.getUserID())) {
                return user.getTransactionHistory();
            }
        }
        throw new IllegalArgumentException("Id does not exist");
    }

    public List<Long> getFriends(Long userID){
        for (User user : users) {
            if (userID.equals(user.getUserID())) {
                return user.getFriends();
            }
        }
        throw new IllegalArgumentException("Id does not exist");
    }

    public void addFriend(Long userID, Long friendID){
        Optional<User> userWithGivenId = getUser(friendID);
        if(userWithGivenId.isEmpty()) {
            throw new IllegalArgumentException("friendID does not exist");
        }
        for (User user : users) {
            if (userID.equals(user.getUserID())) {
                user.addFriend(friendID);
                return;
            }
        }
        throw new IllegalArgumentException("Id does not exist");
    }

    public void removeFriend(Long userID, Long friendID){
        Optional<User> userWithGivenId = getUser(friendID);
        if(userWithGivenId.isEmpty()) {
            throw new IllegalArgumentException("friendID does not exist");
        }
        for (User user : users) {
            if (userID.equals(user.getUserID())) {
                user.removeFriend(friendID);
                return;
            }
        }
        throw new IllegalArgumentException("Id does not exist");
    }

}
