package com.example.CurrencyExchange.controller;

import com.example.CurrencyExchange.dto.UserInput;
import com.example.CurrencyExchange.model.Transaction;
import com.example.CurrencyExchange.model.User;
import com.example.CurrencyExchange.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(path = "/users")
    public void insertUser(@RequestBody UserInput newUser) {
        // US-01 create account
        try {
            userService.insertUser(newUser);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping(path = "/users/{id}")
    public User getUser(@PathVariable("id") Long id) {
        // requires authentication
        User userWithTheGivenId = userService.getUser(id);
        if (userWithTheGivenId == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userWithTheGivenId;
    }

//    @PostMapping(path = "/users/{id}")
//    public void editMyAccount(@PathVariable("id") Long existingUserId, @RequestBody User newUser){
//        // US-03 edit account
//        // requires authentication
//        try {
//            userService.editUser(existingUserId, newUser);
//        } catch (IllegalArgumentException e) {
//            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//    }

    @PostMapping(path = "/users/{id}/delete")
    public void deleteMyAccount(@PathVariable("id") Long id){
        // US-04 delete account
        // requires authentication
        try {
            userService.deleteUser(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

//    @GetMapping(path = "/users/portfolio")
//    public User getUserPortfolio(@RequestParam("id") Long id) {
//        // US-09 search portfolio
//        // no authentication
//        Optional<User> userWithTheGivenId = userService.getUser(id);
//        if (userWithTheGivenId.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//        return userWithTheGivenId.get();
//    }

//    @GetMapping(path = "/users/{id}/friends")
//    public List<Long> getFriends(@PathVariable("id") Long id) {
//        // requires authentication
//        try {
//            return userService.getFriends(id);
//        } catch (IllegalArgumentException e) {
//            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//    }

//    @PostMapping(path = "/users/{id}/friends/add")
//    public void addFriend(@PathVariable("id") Long id, @RequestBody Long friendId){
//        // US-15 Add Friends
//        // requires authentication
//        try {
//            userService.addFriend(id, friendId);
//        } catch (IllegalArgumentException e) {
//            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//    }
//    @PostMapping(path = "/users/{id}/friends/remove")
//    public void removeFriend(@PathVariable("id") Long id, @RequestBody Long friendId){
//        // US-16 Remove Friends
//        // requires authentication
//        try {
//            userService.removeFriend(id, friendId);
//        } catch (IllegalArgumentException e) {
//            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//    }


//    @GetMapping(path = "/users/{id}/transactions")
//    public List<Transaction> getTxHistory(@PathVariable("id") Long id) {
//        // US-07 My Transaction History
//        // requires authentication
//        try {
//            return userService.getTxHistory(id);
//        } catch (IllegalArgumentException e) {
//            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//    }




}