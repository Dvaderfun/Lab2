package oop;

public class BankAccount {

    private String name;
    private double balance;

    public void deposit(double amount) {
        balance = balance + amount;
    }

    public boolean withdraw(double amount) {
        balance = balance - amount;
        return true;
    }
}
