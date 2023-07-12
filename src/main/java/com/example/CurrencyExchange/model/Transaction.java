package com.example.CurrencyExchange.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;

@Entity
public class Transaction {
    @Id
    private Long transactionID;
    private Long sellerID;
    private Long buyerID;
    private String currencyCodeFrom;
    private String currencyCodeTo;
    private float rate;
    private float amount;
    private float price;
    private LocalDate expiryDate;
    private boolean isApproved;

    public Transaction(Long transactionID, Long sellerID, Long buyerID, String currencyCodeFrom, String currencyCodeTo, float rate, float amount, LocalDate expiryDate, boolean isApproved) {
        this.transactionID = transactionID;
        this.sellerID = sellerID;
        this.buyerID = buyerID;
        this.currencyCodeFrom = currencyCodeFrom.toLowerCase();
        this.currencyCodeTo = currencyCodeTo.toLowerCase();
        this.rate = rate;
        this.amount = amount;
        this.price = rate * amount;
        this.expiryDate = expiryDate;
        this.isApproved = isApproved;
    }

//    public Transaction()

    public Transaction() {

    }

    public Long getTransactionID() {
        return transactionID;
    }

    public Long getSellerID() {
        return sellerID;
    }

    public Long getBuyerID() {
        return buyerID;
    }

    public String getCurrencyCodeFrom() {
        return currencyCodeFrom;
    }

    public String getCurrencyCodeTo() {
        return currencyCodeTo;
    }

    public float getRate() {
        return rate;
    }

    public float getAmount() {
        return amount;
    }

    public float getPrice() {
        return price;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public void setSellerID(Long sellerID) {
        this.sellerID = sellerID;
    }

    public void setBuyerID(Long buyerID) {
        this.buyerID = buyerID;
    }

    public void setCurrencyCodeFrom(String currencyCodeFrom) {
        this.currencyCodeFrom = currencyCodeFrom;
    }

    public void setCurrencyCodeTo(String currencyCodeTo) {
        this.currencyCodeTo = currencyCodeTo;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public void setRate() {
        URL url;
        try {
            url = new URL("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/" +
                    currencyCodeFrom + "/" + currencyCodeTo + ".json");
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rateNode = mapper.readTree(response.toString());
            this.rate = rateNode.get(currencyCodeTo).asLong();
            this.price = rate*amount;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
