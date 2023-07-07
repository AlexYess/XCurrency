package com.example.CurrencyExchange.controller;

import com.example.CurrencyExchange.model.Currency;
import com.example.CurrencyExchange.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class CurrencyController {
    private final CurrencyService currencyService;
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }
    @GetMapping(path = "/currencies")
    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }
    @PostMapping(path = "/currencies")
    public void insertCurrency(@RequestBody Currency newCurrency) {
        try {
            currencyService.insertCurrency(newCurrency);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    @GetMapping(path = "/currencies/{currencyCode}")
    public Currency getCurrencyCode(@PathVariable("currencyCode") String code) {
        Optional<Currency> currentCurrency = currencyService.getCurrency(code);
        if (currentCurrency.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return currentCurrency.get();
    }
}
