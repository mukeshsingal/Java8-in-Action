1. Introduction 
2. Ways to define Thread
    1. By Extend Thread class
    2. By implementing Runnable interface
3. Get and Set name of thread 
4. Thread Priority 
5. The method to prevent thread execution 
    1. yield()
    2. join()
    3. sleep()
6. Synchronization 
7. InterThread Communication 
8. Deadlock 
9. Deamon Threads
10. Multithreading enhancements


##1. Introduction

* Multitasking
  - Executing multiple task at same time
  - main objective is to reduce response time of system and to improve performance. 
  - There are two types of multi tasking
     * Thread based
        -  Executing several independent task simultaneously where each task is independent part of same program is called thread based multitasking. 
        - Best suitable at programmatic level
        - to develop video games, animations etc, web servers.    
     * Process based
        - each task is separate independent process.
        - Best suitable at OS level 
            