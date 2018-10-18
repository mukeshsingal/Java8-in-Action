package com.java.action.streams;

public class Transaction {
    Currency currency;
    int amount;

    Transaction(Currency currency, int amount){
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public int getAmount() {
        return amount;
    }
}
