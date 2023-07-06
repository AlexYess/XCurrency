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
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
    public void setPrice(float price) {
        this.price = price;
    }
}
