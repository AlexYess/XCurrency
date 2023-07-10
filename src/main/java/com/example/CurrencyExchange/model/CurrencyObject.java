package com.example.CurrencyExchange.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class CurrencyObject {
    @Id
    @Column(unique = true, nullable = false)
    private String currencyCode;
    @Column(nullable = false)
    private String name;

    public CurrencyObject() {
    }
    public CurrencyObject(String currencyCode, String name) {
        this.currencyCode = currencyCode;
        this.name = name;
    }
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
}
