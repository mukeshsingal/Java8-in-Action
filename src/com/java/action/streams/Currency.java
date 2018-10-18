package com.java.action.streams;

import java.util.HashMap;

public class Currency {

    public final String currencyName;
    private static HashMap<String, Currency> availableCurrencies = new HashMap<>();

    private Currency(String name) {
        this.currencyName = name;
    }

    public static Currency get(String currencyName){
        if(availableCurrencies.get(currencyName) == null){
            availableCurrencies.put(currencyName, new Currency(currencyName));
        }
        return availableCurrencies.get(currencyName);
    }
}
