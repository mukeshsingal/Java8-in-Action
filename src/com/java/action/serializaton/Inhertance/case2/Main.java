package com.java.action.serializaton.Inhertance.case2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        Dog myPet = new Dog();

        myPet.i = 888;
        myPet.j = 999;

        FileOutputStream fileOutputStream = new FileOutputStream("abc.abs");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(myPet);

        FileInputStream fileInputStream = new FileInputStream("abc.abs");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Dog myDogRevived = (Dog) objectInputStream.readObject();
        System.out.println(myDogRevived.i);
        System.out.println(myDogRevived.j);


    }
}
