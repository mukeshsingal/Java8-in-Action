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
    
    Case 1. Parent implements Serializable but child class doesn't 
    * If parent is serializable then all the child will be serializable.
           
    Case 2. Child implements Serializable but parent doesn't
    * If any instance variable is coming from parent which is not implementing serializable, JVM ignores then original 
        value and save default value of data type during serialization.
    * During Deserialization JVM found any parent class Non-Serializable then JVM will start instance control flow. During Instance control flow JVM will 
        * Identification of instance members -> assign parent class members in child class  
        * Execution of instance variable assignments and instance blocks.-> do the assignments specified in parent class.
        * Execution of Constructor. -> Invoke default Constructor of parent. If not found JVM will throw InvalidClassException. 

5. Externalization 

    **Problems with Serialization**
     1. In Serialization everything is taken care by JVM and programmer doesn't have any control over flow which makes Serialization very easy to implement.
     2. Total Objects will be Serialized, we cannot do partial Serialization which sometime cause performance issues. 
     
     Vice versa is true for Externalization. 
     
    **Implementation**
    1. class needs to implement Externalizable Interface.
    2. Need to write two method `readExternal()` and `writeExternal()` 
    3. `writeExternal()` 
        * Invoked at time of Serialization.
        * Write properties to a file 
    3. `readExternal()` -> Invoked at time of DeSerialization
        * read properties from a file to object.
        * JVM will call this method and this method should read value and assign to current object. JVM will return the same object to caller.
        * JVM will call default constructor to create object. In case public default constructor is not present, JVM throws InvalidClassException.
        * **transient** keyword is not required in Externalization.    

6. Difference between Serialization and Externalization

    |Serialization|Externalization|
    |---|---|
    |It is meant to default Serialization|It is meant to Customize Serialization|
    |JVM takes cares of everything|Programmer have control in Externalization|
    |Always save complete object|Based on requirement fields can be Serialized|
    |Performance is low|Performance is High|
    |Serializable is marker interface | Externalizable contains two methods|
    |Doesn't need Serialized class public default constructor| Must contain public default constructor in Externalizable implementation|
    |transient keyword is used | transient keyword is not used|
         
7. **SerialVersionUID**
    * In Serialization sender and receiver need not to be same.
    * In Serialization sender and receiver can be at different locations.
    * In Serialization sender and receiver can be on different machines and OS and fileSystem.
    * .class file should be present in both sender and receiver machine.
    * Just the state of object is travelling from sender to receiver but not .class file.
    * Sender Serialize and receiver Deserialize.
    * With every object at time of serialization, sender side JVM save one unique id based on class file. This serialVersionUID will be used by receiver to verify if it is correct object or not. 

    **Problems with default SerialVersionUID:** 
    1. both sender and receiver must used same version JVM 
    2. After serialization, receiver side class modified. So default UID for receiver will be different. So sender and receiver must use same class file.
    
    **Custom SerialVersionUID**
    ```java
       private static final long serialVersionUID = 1L;
    ``` 
    


