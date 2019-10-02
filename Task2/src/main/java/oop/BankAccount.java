package oop;

public class BankAccount {

    private String name;
    private double balance;
    private double transactionFee;

    public void deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
        }
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount + transactionFee <= balance) {
            balance = balance - (amount + transactionFee);
            return true;
        } else {
            return false;
        }
    }

    public void setTransactionFee(double fee) {
        transactionFee = fee;
    }

    public boolean transfer(BankAccount receiver, double amount) {
        if (withdraw(amount)) {
            receiver.balance = receiver.balance + amount;
            return true;
        } else {
            return false;
        }
    }
}