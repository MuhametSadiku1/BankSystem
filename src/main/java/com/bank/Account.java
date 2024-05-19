package main.java.com.bank;

import java.util.ArrayList;
import java.util.List;

import main.java.com.bank.exceptions.InsufficientFundsException;

public class Account {
    private static int counter = 1;
    private int id;
    private String userName;
    private double balance;
    private List<Transaction> transactions;

    public Account(String userName, double initialBalance) {
        this.id = counter++;
        this.userName = userName;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (balance < amount) {
            throw new InsufficientFundsException("Insufficient funds.");
        }
        balance -= amount;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
