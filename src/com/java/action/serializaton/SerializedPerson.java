package com.java.action.serializaton;

import java.io.*;

public class SerializedPerson {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person mukesh = new Person();

        save(mukesh);
        read();
    }

    public static void save(Person person) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream("Mukesh.ser");

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(person);
    }

    public static void read() throws IOException, ClassNotFoundException{
        FileInputStream fileInputStream = new FileInputStream("Mukesh.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Person mukeshCopy = (Person) objectInputStream.readObject();
        System.out.println(mukeshCopy.name);
        System.out.println(mukeshCopy.age);
    }
}
