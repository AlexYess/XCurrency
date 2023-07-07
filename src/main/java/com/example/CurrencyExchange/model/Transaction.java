package com.example.CurrencyExchange.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpClient;
import java.time.LocalDate;
import java.net.*;
import java.io.*;

public class Transaction {
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
        this.currencyCodeFrom = currencyCodeFrom;
        this.currencyCodeTo = currencyCodeTo;
        this.rate = rate;
        this.amount = amount;
        this.price = rate * amount;
        this.expiryDate = expiryDate;
        this.isApproved = isApproved;
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

    public void setRate() throws Exception{
        URL url = new URL("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/" + currencyCodeFrom + "/" + currencyCodeTo + ".json");
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));
        String input = in.toString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rateNode = mapper.readTree(input);
        this.rate = rateNode.get(currencyCodeTo.toString()).asLong();
//        System.out.println(rateNode.get(currencyCodeTo.toString()).asText());
        in.close();
    }
}
