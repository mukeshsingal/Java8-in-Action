package com.java.action.serializaton.Externalizable;

import java.io.*;

public class Main implements Externalizable {
    String s;
    int i;
    int j;

    public Main() {
        System.out.println("Main Constructor called");
    }

    public Main(String s, int i, int j) {
        this.s = s;
        this.i = i;
        this.j = j;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        /* Save only these properties */
        System.out.println("writeExternal called ");
        out.writeObject(s);
        out.writeObject(i);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("ReadExternal called ");
        /* Read below properties from file */
        s = (String) in.readObject();
        i = (int) in.readObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Main object = new Main("Mukesh", 10, 20);

        /* Serialization */
        System.out.println("Serialization started");
        FileOutputStream fileOutputStream = new FileOutputStream("abs.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);

        /* Deserialization */
        System.out.println("Deserialization started");
        FileInputStream fileInputStream = new FileInputStream("abs.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Main newObject = (Main) objectInputStream.readObject();
        System.out.println(newObject.i);
        System.out.println(newObject.s);
    }
}
