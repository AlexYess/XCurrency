package com.example.CurrencyExchange.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
public class Currency {
    @Id
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
}
