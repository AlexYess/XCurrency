package com.example.CurrencyExchange.dto;

import com.example.CurrencyExchange.model.Currency;

public class CurrencyInput {
    private String currencyCode;
    private String name;

    public String getCurrencyCode() {
        return currencyCode;
    }
    public String getName() {
        return name;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Currency toNewCurrency() {
        Currency newCurrency = new Currency();

        newCurrency.setCurrencyCode(this.currencyCode);
        newCurrency.setName(this.name);

        return newCurrency;
    }
}
