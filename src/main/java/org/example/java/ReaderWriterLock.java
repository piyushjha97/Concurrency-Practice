package org.example.java;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReaderWriterLock {

    private  int count = 0;
    private final ReentrantReadWriteLock readerWriterLock = new ReentrantReadWriteLock();


    public void run() {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        System.out.println("value of count is: " + count);
        executorService.submit(this::read);
        CompletableFuture.runAsync(this::write,executorService)
                .thenRun(()->System.out.println("value of count is: " + count))
                .join();
        executorService.submit(this::read);
        System.out.println("value of count is: " + count);
        executorService.submit(this::read);


    }

    void write() {

        //acquire Write lock
        readerWriterLock.writeLock().lock();
        System.out.println("Write lock acquired for thread: "+ Thread.currentThread().getName());
        try {
            count+=5;
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception thrown");
        } finally {
            System.out.println("Write lock released for thread: "+ Thread.currentThread().getName());
            readerWriterLock.writeLock().unlock();
        }
    }

    void read() {

        //acquire read lock
        readerWriterLock.readLock().lock();
        System.out.println("Read lock acquired for thread: "+ Thread.currentThread().getName());
        try {
            System.out.println("Reading count value as: "+ count);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception thrown");
        } finally {
            System.out.println("Read lock released for thread: "+ Thread.currentThread().getName());
            readerWriterLock.readLock().unlock();
        }
    }
}
