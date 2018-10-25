package com.java.action.serializaton.Inhertance.case1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        Dog account = new Dog();

        FileOutputStream fileOutputStream = new FileOutputStream("abc.abs");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(account);

        FileInputStream fileInputStream = new FileInputStream("abc.abs");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Dog accounts = (Dog) objectInputStream.readObject();
        System.out.println(accounts.i);
        System.out.println(accounts.j);


    }
}
