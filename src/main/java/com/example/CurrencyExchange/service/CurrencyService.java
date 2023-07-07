package com.example.CurrencyExchange.service;

import com.example.CurrencyExchange.model.Currency;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CurrencyService {
    private final List<Currency> currencyList = new ArrayList<>();

    public List<Currency> getAllCurrencies() {
        return currencyList;
    }
    public void insertCurrency(Currency newCurrency) {
        Optional<Currency> currentCurrency = getCurrency(newCurrency.getCurrencyCode());
        if (currentCurrency.isPresent()) {
            throw new IllegalArgumentException("Currency already present");
        }
        currencyList.add(newCurrency);
    }
    public Optional<Currency> getCurrency(String code) {
        for (Currency currency : currencyList) {
            if (code.equals(currency.getCurrencyCode())) {
                return Optional.of(currency);
            }
        }
        return Optional.empty();
    }
}
