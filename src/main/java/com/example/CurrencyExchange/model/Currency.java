package com.example.CurrencyExchange.model;

public class Currency {
    private String currencyCode;
    private String lastUpdatedDate;
    private float price;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public float getPrice() {
        return price;
    }
}
