package com.java.action;

import com.java.action.impl.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Farmer {
    public static void main(String[] args) {

        List<Apple> appleInventory = Arrays.asList(
                new Apple("green", 120),
                new Apple("red", 230),
                new Apple("green", 155),
                new Apple("red", 270));

        List<Orange> orangeInventory = Arrays.asList(
                new Orange(120),
                new Orange(30),
                new Orange(155),
                new Orange(70));

        //Sort using anonymous class
        /*orangeInventory.sort(new Comparator<Orange>() {
            @Override
            public int compare(Orange o1, Orange o2) {
                return o1.getWeight() < o2.getWeight() ? -1 : 1;
            }
        });*/


        //Sort using lambda
        //orangeInventory.sort((o1, o2) -> o1.getWeight() < o2.getWeight() ? -1 : 1);

        //Using Method Referencing
        orangeInventory.sort(Comparator.comparing(Orange::getWeight));

        prettyPrintApple(orangeInventory, (orange) -> "Orange " + String.valueOf((orange.getWeight())));

        //Using Implementation class
        //List<Apple> greenApples = filter(appleInventory, new FilterGreenPredicateImpl());

        //Using Anonymous class
        /*List<Apple> greenApples = filter(appleInventory, new FilterPredicate() {
            @Override
            public boolean filter(Apple apple) {
                return "green".equals(apple.color);
            }
        });*/

        //Using Lambdas
        List<Apple> greenApples = filter(appleInventory, (apple) -> "green".equals(apple.getColor()));
        List<Orange> heavyOranges = filter(orangeInventory, (orange) -> orange.getWeight() > 150);

        prettyPrintApple(greenApples, (apple) -> "Apple Weight -> " + String.valueOf((apple.getWeight())));
        prettyPrintApple(heavyOranges, (orange) -> "Orange Weight -> " + String.valueOf((orange.getWeight())));
    }

    // Behaviour Parameterization
    public static <T> List<T> filter(List<T> inventory, FilterPredicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T item : inventory) {
            if (p.filter(item)) { //FilterPredicate simualate Behaviour parameterization
                result.add(item);
            }
        }
        return result;
    }

    //Behaviour Parameterization
    public static <T> void prettyPrintApple(List<T> inventory, PrintFormatter<T> p) {
        for (T item : inventory) {
            String characteristics = p.accept(item);
            System.out.println(characteristics);
        }
    }
}
