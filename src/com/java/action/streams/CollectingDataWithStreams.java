package com.java.action.streams;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class CollectingDataWithStreams {

    static List<Transaction> transactions = Arrays.asList(
            new Transaction(Currency.get("INR"), 2000),
            new Transaction(Currency.get("INR"), 250),
            new Transaction(Currency.get("INR"), 20),
            new Transaction(Currency.get("INR"), 9990),
            new Transaction(Currency.get("USD"), 780),
            new Transaction(Currency.get("USD"), 65),
            new Transaction(Currency.get("USD"), 9990),
            new Transaction(Currency.get("ESD"), 1000)
    );

    public static void main(String[] args) {
        /*
            Task 1: Group a list of transactions by currency to obtain the
            sum of the values of all transactions with that currency (returning
            a Map<Currency, Integer>)
        */

        // Without Using Collection
        /*Map<Currency, List<Transaction>> groupedByCurrency = new HashMap<>();
        for (Transaction transaction: transactions){
            Currency currency = transaction.getCurrency();

            List<Transaction> transactionsForCurrency = groupedByCurrency.get(currency);
            if(transactionsForCurrency == null){
                List<Transaction> init = new ArrayList<>();
                init.add(transaction);
                groupedByCurrency.put(currency, init);
            }
            else {
                transactionsForCurrency.add(transaction);
                groupedByCurrency.put(currency, transactionsForCurrency);
            }
        }*/

        //WithStream
        Map<Currency, List<Transaction>> groupedByCurrency  =
                transactions.stream()
                .collect(groupingBy(Transaction::getCurrency));

        //Print Grouping
        for(Map.Entry<Currency, List<Transaction>> t : groupedByCurrency.entrySet()){
            System.out.println("\n" +  t.getKey().currencyName);
            t.getValue().forEach((trans) -> {System.out.print(" " + trans.getAmount());});
        }


    }
}
