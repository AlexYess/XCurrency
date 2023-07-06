package com.example.CurrencyExchange.controller;

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
    public void insertUser(@RequestBody User newUser) {
        try {
            userService.insertUser(newUser);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping(path = "/users/{id}")
    public User getUser(@PathVariable("id") Long id) {
        Optional<User> userWithTheGivenId = userService.getUser(id);
        if (userWithTheGivenId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return userWithTheGivenId.get();
    }
}