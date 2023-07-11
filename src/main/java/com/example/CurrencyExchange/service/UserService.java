package com.example.CurrencyExchange.service;

import com.example.CurrencyExchange.dto.FriendInput;
import com.example.CurrencyExchange.dto.UserInput;
import com.example.CurrencyExchange.model.Friend;
import com.example.CurrencyExchange.model.User;
import com.example.CurrencyExchange.repository.FriendRepository;
import com.example.CurrencyExchange.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


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
        userRepository.save(newUser);
        return newUser;
    }

    public User getUser(Long userID) {
        return userRepository.findByUserID(userID);
    }


    public void deleteUser(Long existingUserId){
        userRepository.deleteById(existingUserId);
    }


    public Friend getFriends(Long userID){
//        return friendRepository.findByUserID(userID);
        return friendRepository.findByUserID(userID);
    }


    public Friend addFriend(FriendInput friend){
        Friend newFriend = friend.toNewFriend();
        friendRepository.save(newFriend);
        return newFriend;
    }



    public void removeFriend(Long userID, Long friendID){
        friendRepository.deleteByUserIDAndFriendID(userID, friendID);
    }

//    private boolean isValid(Friend newFriend) {
//        return Objects.nonNull(newFriend)
//                && Objects.nonNull(newFriend.)
//                && Objects.nonNull(newFriend.getText())
//                && Objects.nonNull(newFriend.getAuthor());
//    }
}
