package com.java.action.serializaton.CustomSerialization;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Accounts implements Serializable {
    String name = "mukesh";
    transient String password = "password";
    transient int pin = 123;

    /* This signature is static else JVM won't call this */
    private void writeObject(ObjectOutputStream objectOutputStream) throws Exception {
        objectOutputStream.defaultWriteObject(); //Do default Serialization
        String pwd = "***&&&&&&&&&&";
        int myPin = 321;
        objectOutputStream.writeObject(pwd);
        objectOutputStream.writeObject(myPin);
    }

    /* This signature is static else JVM won't call this */
    private void readObject(ObjectInputStream objectInputStream) throws Exception {
        objectInputStream.defaultReadObject(); //Do default Serialization
        String pwd = (String) objectInputStream.readObject();
        password = pwd.substring(3);

        int myPin = (int) objectInputStream.readObject();
        pin = myPin;
    }
}
