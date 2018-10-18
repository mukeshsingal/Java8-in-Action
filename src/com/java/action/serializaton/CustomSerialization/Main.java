package com.java.action.serializaton.CustomSerialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        Accounts account= new Accounts();
        System.out.println(account.name);
        System.out.println(account.password);
        System.out.println(account.pin);

        FileOutputStream fileOutputStream = new FileOutputStream("abc.abs");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(account);

        FileInputStream fileInputStream = new FileInputStream("abc.abs");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Accounts accounts = (Accounts) objectInputStream.readObject();
        System.out.println(accounts.name);
        System.out.println(accounts.password);
        System.out.println(accounts.pin);

    }
}
