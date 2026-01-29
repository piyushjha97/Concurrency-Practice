package org.example;

public class BankAccountUtil {



    public void perform() throws InterruptedException {
        BankAccount bankAccount = new BankAccount();

        bankAccount.createBankAccount("userId1",400);

        bankAccount.createBankAccount("userId2",20);
        bankAccount.createBankAccount("userId3",100);


        bankAccount.withdraw("userId1",100);
        bankAccount.withdraw("userId2",100);
        bankAccount.withdraw("userId1",2000);

}
}
