package com.example.CurrencyExchange.service;

import com.example.CurrencyExchange.dto.UserInput;
import com.example.CurrencyExchange.model.Transaction;
import com.example.CurrencyExchange.model.User;
import com.example.CurrencyExchange.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User insertUser(UserInput user) {
        // US-01 create account
        User newUser = user.toNewUser();
        userRepository.save(newUser);
        return newUser;
    }

    public User getUser(Long userID) {
//        System.out.println(userRepository.findByUserID(userID));
//        System.out.println(userRepository.findByUserID(userID).getClass().getName());

        return userRepository.findByUserID(userID);

    }

//    public void editUser(Long existingUserId, User newUser){
//        if(!newUser.getUserID().equals(existingUserId)){
//            throw new IllegalArgumentException("Id does not match");
//        }
//
//        for (User user : users) {
//            if (existingUserId.equals(user.getUserID())) {
//                int index = users.indexOf(user);
//                users.set(index, newUser);
//                return;
//            }
//        }
//        throw new IllegalArgumentException("Id does not exist");
//    }

    public void deleteUser(Long existingUserId){
        userRepository.deleteById(existingUserId);
    }

//    public List<Long> getTxHistory(Long userID){
//        for (User user : users) {
//            if (userID.equals(user.getUserID())) {
//                return user.getTransactionHistory();
//            }
//        }
//        throw new IllegalArgumentException("Id does not exist");
//    }

//    public List<Long> getFriends(Long userID){
//        for (User user : users) {
//            if (userID.equals(user.getUserID())) {
//                return user.getFriends();
//            }
//        }
//        throw new IllegalArgumentException("Id does not exist");
//    }

//    public void addFriend(Long userID, Long friendID){
//        Optional<User> userWithGivenId = getUser(friendID);
//        if(userWithGivenId.isEmpty()) {
//            throw new IllegalArgumentException("friendID does not exist");
//        }
//        for (User user : users) {
//            if (userID.equals(user.getUserID())) {
//                user.addFriend(friendID);
//                return;
//            }
//        }
//        throw new IllegalArgumentException("Id does not exist");
//    }

//    public void removeFriend(Long userID, Long friendID){
//        Optional<User> userWithGivenId = getUser(friendID);
//        if(userWithGivenId.isEmpty()) {
//            throw new IllegalArgumentException("friendID does not exist");
//        }
//        for (User user : users) {
//            if (userID.equals(user.getUserID())) {
//                user.removeFriend(friendID);
//                return;
//            }
//        }
//        throw new IllegalArgumentException("Id does not exist");
//    }

}
