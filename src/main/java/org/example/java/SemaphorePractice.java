package org.example.java;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphorePractice {


    private final Semaphore semaphore = new Semaphore(1);
    AtomicInteger count = new AtomicInteger(0);
    volatile int flag = 0;

    public void run() throws InterruptedException {

        Thread thread1 = new Thread(this::acquireCriticalSection);
        Thread thread2 = new Thread(this::anotherSection);

        int times = 10;

            thread1.start();
            thread2.start();

//            thread2.join();
//            thread1.join();

       // System.out.println("Thread :" + Thread.currentThread().getName()+ " read flag value as: "+ flag);

    }

    void acquireCriticalSection() {

        System.out.println("Critical Section method called by Thread: "+ Thread.currentThread().getName());

        try {
            semaphore.acquire();
            Thread.sleep(10);
            System.out.println("Critical Section entered by Thread: "+ Thread.currentThread().getName());
            if (flag == 0) {
//                System.out.println("Thread :" + Thread.currentThread().getName()+ " read flag value as zero so incrementing count value by 1");
//                count.set(count.get()+1);
                flag = 1;
            } else {
                System.out.println("Thread :" + Thread.currentThread().getName()+ " read count value as: "+ count);
                System.out.println("Thread :" + Thread.currentThread().getName()+ " read flag value as: "+ flag);
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Exception thrown by thread: "+ Thread.currentThread().getName());
        } finally {
            semaphore.release();
            System.out.println("Finally block reached by thread: "+ Thread.currentThread().getName());
        }
    }

    private void anotherSection() {

        while (flag == 0) {
            System.out.println("Thread :" + Thread.currentThread().getName()+ " is in while loop since flag value is: "+ flag);
        }
        System.out.println("Thread : " + Thread.currentThread().getName()+ " exist while loop as  flag value changed to: "+ flag);
    }
}
