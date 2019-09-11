package oop;

public class BankAccount {

    private String name;
    private double balance;
    private double transactionFee;

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public void deposit(double amount) {
        balance = balance + amount;
    }

    public boolean withdraw(double amount) {
        if (amount + transactionFee > balance)
            return false;

        balance = balance - (amount + transactionFee);
        return true;
    }

    public boolean transfer(BankAccount receiver, double amount) {
        if (amount < 0)
            return false;

        if (withdraw(amount))
            receiver.deposit(amount);

        return true;
    }
}
