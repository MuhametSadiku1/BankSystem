package main.java.com.bank;

import java.util.ArrayList;
import java.util.List;

import main.java.com.bank.exceptions.AccountNotFoundException;
import main.java.com.bank.exceptions.InsufficientFundsException;

public class Bank {
    private String name;
    private List<Account> accounts;
    private double totalTransactionFees;
    private double totalTransferAmount;
    private double flatFee;
    private double percentFee;

    public Bank(String name, double flatFee, double percentFee) {
        this.name = name;
        this.accounts = new ArrayList<>();
        this.totalTransactionFees = 0.0;
        this.totalTransferAmount = 0.0;
        this.flatFee = flatFee;
        this.percentFee = percentFee;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account getAccountById(int accountId) throws AccountNotFoundException {
        return accounts.stream()
                .filter(account -> account.getId() == accountId)
                .findFirst()
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + accountId));
    }

    public void applyTransactions(Transaction transaction) throws InsufficientFundsException, AccountNotFoundException {
        Account originatingAccount = getAccountById(transaction.getOriginatingAccountId());
        Account resultingAccount = getAccountById(transaction.getResultingAccountId());

        double fee = transaction.isFlatFee() ? flatFee : transaction.getAmount() * percentFee / 100;
        double totalAmount = transaction.getAmount() + fee;

        if (originatingAccount.getBalance() < totalAmount) {
            throw new InsufficientFundsException("Not enough funds for the transaction.");
        }

        originatingAccount.withdraw(totalAmount);
        resultingAccount.deposit(transaction.getAmount());

        totalTransactionFees += fee;
        totalTransferAmount += transaction.getAmount();

        originatingAccount.addTransaction(transaction);
        resultingAccount.addTransaction(transaction);
    }

    public double getTotalTransactionFees() {
        return totalTransactionFees;
    }

    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
