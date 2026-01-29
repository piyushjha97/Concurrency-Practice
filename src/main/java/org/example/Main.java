package org.example;

import org.example.java.ThreadPractice;

import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("Main method called.");

        ThreadPractice threadPractice = new ThreadPractice();


       threadPractice.perform();

    }
}