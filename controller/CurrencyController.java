package controller;

import model.Currency;
import service.CurrencyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurrencyController {
    private final CurrencyService currencyService;
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }
    public List<Currency> GetAllCurrencies() {
        return currencyService.GetAllCurrencies();
    }
    public void insertCurrency(Currency newCurrency) {
        try {
            currencyService.insertCurrency(newCurrency);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    public Optional<Currency> GetCurrencyCode(String code) {
        if (currencyService.GetCurrencyCode(code).isPresent()) {
            throw new IllegalArgumentException("Currency already present");
        }
        return Optional.of(currencyService.GetCurrencyCode(code).get());
    }
}
