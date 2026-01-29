package org.example;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConcurrencyUtil {




    public void test() throws InterruptedException {


        Thread thread = new Thread(() -> {

            Thread child1 = new Thread(() -> {
                try {
                    performHeavyMath();
                } catch (InterruptedException e) {
                    System.out.println("Child1 thread was interrupted.");
                }
            });

            child1.start();
            System.out.println("Child thread was started.");

            try {
                child1.join(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (child1.isAlive()) {
                System.out.println("Child1 thread is taking too long, interrupting it.");
                child1.interrupt();
            }
        });

        thread.start();
        System.out.println("Parent thread was started.");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                System.out.println("sleeping thread2 for i = "+ i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread2.start();

        thread.join();

        System.out.println("Thread1 was ended but thread2 might still be running.");


    }

    private void performHeavyMath() throws InterruptedException {

        System.out.println("child thread method was started.");

        for (int i = 0; i < 1_000_000_000; i++) {
            System.out.println("sleeping child thread for i = "+ i);
            Thread.sleep(1000);
        }
    }
}
