package com.example.CurrencyExchange.model;

public class Transaction {
    private Long transactionID;
    private Long sellerID;
    private Long buyerID;
    private String currencyCodeFrom;
    private String currencyCodeTo;
    private float rate;
    private float amount;
    private float price;
    private String expiryDate;
    private boolean isApproved;

    public Transaction(Long transactionID, Long sellerID, Long buyerID, String currencyCodeFrom, String currencyCodeTo, float rate, float amount, String expiryDate, boolean isApproved) {
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
}
