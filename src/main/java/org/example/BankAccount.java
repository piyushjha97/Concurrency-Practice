package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;




public class BankAccount {

    private static final Logger log = Logger.getLogger("MyLogger");

    Map<String, Integer> userBankAccount = new HashMap<>();

    public void withdraw(String userId, int amount) throws InterruptedException {

        log.info("Amount withdraw process begins for userId: " + userId + " and amount: " + amount);

        if (!userBankAccount.containsKey(userId)) {
            log.info("Bank account with userId: " + userId + " doesn't exist!!");
            throw new IllegalArgumentException("Bank account with userId: " + userId + " doesn't exist");
        }

        int balance = userBankAccount.get(userId);

        if (balance >= amount) {
            balance -= amount;
            userBankAccount.put(userId,balance);
            log.info("withdraw successful with remaining balance : "+  balance);
        }
        else {
            log.info("withdraw failed as balance is less than amount");
        }

        Thread.sleep(3000);
    }

    public void createBankAccount(String userId,int initialBalance) throws InterruptedException {

        if (userBankAccount.containsKey(userId)) {
            log.info("userId: " + userId + " already exists, please use new userId");
            throw new IllegalArgumentException("userId already exists, please use new userId");
        }
        userBankAccount.put(userId,initialBalance);
        log.info("New bank account created with userId: " + userId + " and initial balance: "+ initialBalance + "!!");
        Thread.sleep(3000);
    }

}
