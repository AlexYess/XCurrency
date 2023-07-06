package com.example.CurrencyExchange.service;

import com.example.CurrencyExchange.model.Currency;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class CurrencyService {
    private List<Currency> currencyList = new ArrayList<>();

    public List<Currency> GetAllCurrencies() {
        return currencyList;
    }
    public void insertCurrency(Currency newCurrency) {
        if (GetCurrencyCode(newCurrency.getCurrencyCode()).isPresent()) {
            throw new IllegalArgumentException("Currency already present");
        }
        currencyList.add(newCurrency);
    }
    public Optional<Currency> GetCurrencyCode(String code) {
        for (Currency currency : currencyList) {
            if (currency.getCurrencyCode().equals(code)) {
                return Optional.of(currency);
            }
        }
        return Optional.empty();
    }
}
