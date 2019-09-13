package oop;

public class BankAccount {

    private String name;
    private double balance;

    public void deposit(double amount) {
        balance = balance + amount;
    }

    public double getBalance() {
        return this.balance;
    }

    public boolean withdraw(double amount) {
        balance = balance - amount;
        return true;
    }

    public void setTransactionFee(double fee) {

    }

    public void transfer(BankAccount other, double amount) {

    }
}