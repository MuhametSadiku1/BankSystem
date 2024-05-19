package main.java.com.bank;

import java.util.Scanner;

import main.java.com.bank.exceptions.AccountNotFoundException;
import main.java.com.bank.exceptions.InsufficientFundsException;

public class BankSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Bank bank;

    public static void main(String[] args) {
        initializeBank();
        displayMenu();
    }

    private static void initializeBank() {
        System.out.println("Enter Bank name:");
        String bankName = scanner.nextLine();
        System.out.println("Enter flat fee amount: ");
        double flatFee = scanner.nextDouble();
        System.out.println("Enter percent fee amount: ");
        double percentFee = scanner.nextDouble();
        scanner.nextLine();

        bank = new Bank(bankName, flatFee, percentFee);
    }

    private static void displayMenu() {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Create an Account");
            System.out.println("2. Perform Transaction");
            System.out.println("3. Withdraw");
            System.out.println("4. Deposit");
            System.out.println("5. List Accounts");
            System.out.println("6. Check Account Balance");
            System.out.println("7. Check Total transaction Fees");
            System.out.println("8. Check Total Transfer Amount");
            System.out.println("9. View Transactions for an Account");
            System.out.println("10. Exit");

            int operationChosen = scanner.nextInt();
            scanner.nextLine();

            switch (operationChosen) {
                case 1 -> createAccount();
                case 2 -> performTransaction();
                case 3 -> withdraw();
                case 4 -> deposit();
                case 5 -> listAccounts();
                case 6 -> checkAccountBalance();
                case 7 -> checkTotalTransactionFees();
                case 8 -> checkTotalTransferAmount();
                case 9 -> viewTransactions();
                case 10 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again. ");
            }
        }
    }

    private static void createAccount() {
        System.out.println("Enter user name:");
        String userName = scanner.nextLine();
        System.out.println("Enter initial balance:");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine();

        Account account = new Account(userName, initialBalance);
        bank.addAccount(account);
        System.out.println("Account created successfully with ID: " + account.getId());
    }

    private static void performTransaction() {
        try {
            System.out.println("Enter originating account ID:");
            int originatingAccountId = scanner.nextInt();
            System.out.println("Enter resulting account ID:");
            int resultingAccountId = scanner.nextInt();
            System.out.println("Enter amount:");
            double amount = scanner.nextDouble();
            System.out.println("Enter transaction reason:");
            scanner.nextLine();
            String reason = scanner.nextLine();
            System.out.println("Is this a flat fee transaction? (true/false):");
            boolean isFlatFee = scanner.nextBoolean();

            Transaction transaction = new Transaction(amount, originatingAccountId, resultingAccountId, reason,
                    isFlatFee);

            bank.applyTransactions(transaction);
        } catch (AccountNotFoundException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void withdraw() {
        try {
            System.out.println("Enter account ID:");
            int accountId = scanner.nextInt();
            System.out.println("Enter amount:");
            double amont = scanner.nextDouble();
            scanner.nextLine();

            Account account = bank.getAccountById(accountId);
            account.withdraw(amont);
            System.out.println("Withdrawal completed successfully.");
        } catch (AccountNotFoundException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deposit() {
        try {
            System.out.println("Enter account ID:");
            int accountId = scanner.nextInt();
            System.out.println("Enter amount:");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            Account account = bank.getAccountById(accountId);
            account.deposit(amount);
            System.out.println("Deposit complered successfully.");
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listAccounts() {
        for (Account account : bank.getAccounts()) {
            System.out.println("ID: " + account.getId() + ", Name: " + account.getUserName() + ", Balance: $"
                    + account.getBalance());
        }
    }

    private static void checkAccountBalance() {
        try {
            System.out.println("Enter account ID:");
            int accountId = scanner.nextInt();
            scanner.nextLine();

            Account account = bank.getAccountById(accountId);
            System.out.println("Account ID: " + accountId + ", Balance: $" + account.getBalance());
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkTotalTransactionFees() {
        System.out.println("Total Transaction Fees: $" + bank.getTotalTransactionFees());
    }

    private static void checkTotalTransferAmount() {
        System.out.println("Total Transfer Amount: $" + bank.getTotalTransferAmount());
    }

    private static void viewTransactions() {
        try {
            System.out.println("Enter account ID:");
            int accountId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Account account = bank.getAccountById(accountId);
            System.out.println("Transactions for account ID " + accountId + ":");
            for (Transaction transaction : account.getTransactions()) {
                System.out.println(transaction);
            }
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
