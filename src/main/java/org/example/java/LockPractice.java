package org.example.java;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import static org.example.Main.GlobalLogger;

public class LockPractice {


    int counter = 0;

    ReentrantLock lock = new ReentrantLock();

    public void run() {

        ExecutorService service = createThreadPool();

        for (int i = 0;i<10;i++) {
            service.submit(createTask());
        }
    }

    Callable<Void> createTask() {
        Callable<Void> task = () -> {
            increment();
            return null;
        };
        return task;
    }

    ExecutorService createThreadPool() {
        return Executors.newFixedThreadPool(5);
    }

    void increment() {
        lock.lock();
        GlobalLogger.info("Acquired Lock by thread: "+ Thread.currentThread().getName());
        try {
            GlobalLogger.info("entered the critical section with counter value as: "+ counter);
            counter++;
            GlobalLogger.info("exiting the critical section by incrementing counter to: "+ counter);
        } finally {
            GlobalLogger.info("unlocking the critical section by thread: "+ Thread.currentThread().getName());
            lock.unlock();
        }
    }
}
