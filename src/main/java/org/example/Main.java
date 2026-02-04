package org.example;

import org.example.java.LockPractice;
import org.example.java.SemaphorePractice;
import org.example.java.ThreadPractice;

import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class Main {

    public static final Logger GlobalLogger = Logger.getLogger("GlobalLogger");
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("Main method called.");

      //  ThreadPractice threadPractice = new ThreadPractice();
    //   threadPractice.perform();

//        BasicPubSub basicPubSub = new BasicPubSub();
//
//        basicPubSub.run();


//        LockPractice lockPractice = new LockPractice();
//        lockPractice.run();

        SemaphorePractice semaphorePractice = new SemaphorePractice();
        semaphorePractice.run();

    }
}