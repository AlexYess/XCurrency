package com.example.CurrencyExchange.controller;

import com.example.CurrencyExchange.dto.FriendInput;
import com.example.CurrencyExchange.dto.UserInput;
import com.example.CurrencyExchange.model.Friend;
import com.example.CurrencyExchange.model.Transaction;
import com.example.CurrencyExchange.model.User;
import com.example.CurrencyExchange.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
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
    @ResponseBody
    public User insertUser(@RequestBody UserInput newUser) {
        // US-01 create account
        try {
            User userInserted = userService.insertUser(newUser);
            if (Objects.isNull(userInserted)){
                throw new IllegalArgumentException("username already taken");
            }else{
                return userInserted;
            }
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping(path = "/users", params = "id")
    public User getUser(@RequestParam("id") Long id) {
        User userWithTheGivenId = userService.getUser(id);
        if (Objects.isNull(userWithTheGivenId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userWithTheGivenId;
    }

    @GetMapping(path = "/users", params = "username")
    public User getUserByUsername(@RequestParam("username") String username) {
        User user = userService.getUserByUsername(username);
        if (Objects.isNull(user)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return user;
    }

    @GetMapping(path = "/users/{id}/delete")
    public void deleteMyAccount(@PathVariable("id") Long id){
        // US-04 delete account
        // requires authentication
        try {
            userService.deleteUser(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping(path = "/users/delete/all")
    public void deleteAllAccounts(){
        // US-04 delete account
        // requires authentication
        try {
            userService.deleteAllAccounts();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }







}