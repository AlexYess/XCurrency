package com.example.CurrencyExchange.service;

import com.example.CurrencyExchange.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();

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

}
