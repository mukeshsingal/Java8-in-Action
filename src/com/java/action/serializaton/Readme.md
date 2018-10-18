#Serialization

The process of writing state of an object to a file is called **Serialization**. But it is the process of converting an object from Java supported form into file supported form or network supported form.

> **FileOutputStream** and **ObjectOutputStream** classes used to achieve Serialization.

The process of reading state of an Object from a file is called **DeSerialization**. But it is the process of converting an object from either file or from network supported form to java supported form.

> **FileInputStream** and **ObjectInputStream** classes used to achieve Deserialization.

> Class which need to be serialized we need to mart it with **Serializable** interface. 

___________________________________________________________________________________________

### Table of Content
1. Transient - final vs static
    * Transient is used Not to serialize field.
    * if we don't want to serialize any field to meet security constrains   declare the variable with transient. Then serialized file can only contains default value of that field.   
    * only valid for fields. 
    * we are saving data permanently for future purpose. So we should save sensitive information. 
    * Serializable concept is only for object not for **static** content. 
    * There is no use of declaring static variable as transient as static variable will not take part in serialization. Field value will come from static context. 
    * There is no use of using transient with **final** as final variables will always participate directly in serialization.
     
         |value|output|
         |---|---|
         |int i = 10 | i = 10|
         |transient int i = 10 | i = 0|
         |static int i = 10 | i = 10|
         |transient static int i = 10 | i = 10|
         |final int i = 10 | i = 10|
         |transient final int i = 10 | i = 10|
    
    * We can serialize any number of object into a file.
    * The order in which we serialize the same order we need to deserializer when we have multiple objects store in serialized file.
    * if we don't know the order of deserialization
        ```java
           class temp {
           public static void main (String[] args) {
               FileInputStream fileInputStream = new FileInputStream("Mukesh.ser");
               ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
               Obejct o = objectInputStream.readObject();
               if(o instanceof Dog) {
                   Dog doggy = (Dog) o;
               }
            else {
                Cat cat = (Cat) o;
               }
           }
        }
        ```     
2. Object graph
    * All the objects which are reachable from current object will be serialize.
    * In object grapth all objects must implement Serializable. 
      
    ```java
    class Dog implements Serializable {
       Cat c = new Cat();
    }
    class Cat implements Serializable {
      Rat rat = new Rat();  
    }
    class Rat implements Serializable {
       int j = 20;
    }
 
    class Serializedemo {
        public static void main(String[] args) {    
             Dog dog = new Dog();
             //Serialize dog will create three object and all will be serialized
             // This is called Object graph.  
           
        } 
    }
    ```
3. Customized serialization
    
    **Need of Customized serialization**
    * In default serialization there is possibility of loss of information. So in that case we should go for Customization Serialization due to  transient keyword.
    * 
    ```java
    class Accounts implements Serializable {
        String name = "mukesh";
        transient String passowrd = "password";
    }
    class CustomSerizable {
        public static void main(String[] args) {
            Accounts accounts = new Accounts();
    
            FileOutputStream fileOutputStream = new FileOutputStream("abs.xy");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(accounts);
    
            FileInputStream fileInputStream = new FileInputStream("abs.xy");
            ObjectInputStream objectInputStream = new ObjectInputSteam(fileInputStream);
            //objectInputStream.readObject().password this will we null due to transient keyword
        }
    }
    ```
    
    **How to implement Customize Serialization**
    * Before and after serialization if we do extra work to recover the information. This is called customized Serialization. 
    * we can implement Customized Serialization by using the following 2 methods 
        1) `private void writeObject(ObjectOutputStream os) throws Exception` 
            * This method will be executed automatically at time of serialization. 
            * Hence if we want to do extra work we have to write code in this method only. 
        2) `private void readObject(ObjectOutputStream os) throws Exception` 
            * This method will be executed automatically at time of de-serialization. 
            * Hence if we want to do extra work we have to write code in this method only.
    Note: 
        1. The above methods are callback method because these methods will be executed automatically by JVM
        2. If we want to do extra work in a class serialization then that class has to provide implementation of above methods
         
4. Serialization wrt inheritance.
    Case 1. Parent implements Serializable but class doesn't 
        * if parent is serializable then all the child will be serializable.
           
    Case 2. Child implements Serializable but parent doesn't
        * If any instance variable is coming from parent which is not implementing serializable, JVM ignores then original 
        value and save default value.
        *   

5. Externalization
6. SerialVersionUID








