package com.example.CurrencyExchange.controller;

import com.example.CurrencyExchange.model.Currency;
import com.example.CurrencyExchange.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public List<Currency> GetAllCurrencies() {
        return currencyService.GetAllCurrencies();
    }
    @PostMapping(path = "/currencies")
    public void insertCurrency(Currency newCurrency) {
        try {
            currencyService.insertCurrency(newCurrency);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    @GetMapping(path = "/currencies/{currencyCode}")
    public Optional<Currency> GetCurrencyCode(String code) {
        if (currencyService.GetCurrencyCode(code).isPresent()) {
            throw new IllegalArgumentException("Currency already present");
        }
        return Optional.of(currencyService.GetCurrencyCode(code).get());
    }
}
