package com.java.action.serializaton.Inhertance.case2;

import java.io.Serializable;

public class Dog extends Animal implements Serializable {
    int j = 20;

    public Dog() {
        System.out.println("Child Class Constructor invoked.");
    }
}
