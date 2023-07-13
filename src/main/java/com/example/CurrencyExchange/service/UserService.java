package com.example.CurrencyExchange.service;

import com.example.CurrencyExchange.dto.FriendInput;
import com.example.CurrencyExchange.dto.UserInput;
import com.example.CurrencyExchange.model.Friend;
import com.example.CurrencyExchange.model.User;
import com.example.CurrencyExchange.repository.FriendRepository;
import com.example.CurrencyExchange.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class UserService {
    private UserRepository userRepository;
    private FriendRepository friendRepository;


    public UserService(UserRepository userRepository, FriendRepository friendRepository) {
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User insertUser(UserInput user) {
        // US-01 create account
        User newUser = user.toNewUser();
        if(Objects.isNull(getUserByUsername(newUser.getUsername()))){
            userRepository.save(newUser);
            return newUser;
        }else{
            return null;
        }

    }

//    public User updateUser(UserInput user) {
////        private Long userID = user.get
//    }

    public User getUser(Long userID) {
        return userRepository.findByUserID(userID);
    }
    public User getUserByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    public void deleteUser(Long existingUserId){
        userRepository.deleteById(existingUserId);
    }


//    public Friend getFriends(Long userID){
//        return friendRepository.findByUserID(userID);
//    }
//
//
//    public Friend addFriend(FriendInput friend){
//        Friend newFriend = friend.toNewFriend();
//        friendRepository.save(newFriend);
//        return newFriend;
//    }
//
//    public void removeFriend(Long userID, Long friendID){
//        friendRepository.deleteByUserIDAndFriendID(userID, friendID);
//    }

}
