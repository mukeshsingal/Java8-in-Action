package com.java.action.serializaton;

import java.io.Serializable;

public class Person implements Serializable {
    String name = "Mukesh";
    transient static int age  = 23;
}
