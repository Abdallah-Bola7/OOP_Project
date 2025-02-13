package service;


import model.Account;
import model.Ewallet;

import java.util.ArrayList;
import java.util.List;

public  class AccountServiceImpl implements AccountService {

    private static List<Account> accounts = new ArrayList<>();
    private ValidationService validationService = new ValidationServiceImpl();


    @Override
    public boolean createAccount(Account account) {
        if (!validationService.validateUserName(account.getUserName())) {
            System.out.println("Invalid username.");
            return false;
        }

        if (!validationService.validatePassword(account.getPassword())) {
            System.out.println("Invalid password.");
            return false;
        }

        for (Account existingAccount : accounts) {
            if (existingAccount.getUserName().equals(account.getUserName())) {
                System.out.println("Account with this username already exists.");
                return false;
            }
        }

        accounts.add(account);
        System.out.println("Account successfully created.");
        return true;
    }

    public void showAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            System.out.println("Existing Accounts:");
            for (Account acc : accounts) {
                System.out.println("Username: " + acc.getUserName());
            }
        }
    }

    @Override
    public boolean deposit(Account account, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount. It must be greater than zero.");
            return false;
        }

        for (Account acc : accounts) {
            if (acc.getUserName().equals(account.getUserName())) {

                if (!acc.isActive()) {
                    System.out.println("Account is not active.");
                    return false;
                }

                acc.setBalance(acc.getBalance() + amount);
                System.out.println("Deposit successful. New balance: " + acc.getBalance());
                return true;
            }
        }

        System.out.println("Account does not exist.");
        return false;
    }



    @Override
    public boolean withdraw(Account account, double amount) {
        if (!validationService.validateUserName(account.getUserName())) {
            System.out.println("Invalid username format.");
            return false;
        }

        if (!validationService.validatePassword(account.getPassword())) {
            System.out.println("Invalid password format.");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount. Amount must be greater than zero.");
            return false;
        }

        for (Account acc : accounts) {
            if (acc.getUserName().equals(account.getUserName()) &&
                    acc.getPassword().equals(account.getPassword())) {

                if (!acc.isActive()) {
                    System.out.println("Account is not active.");
                    return false;
                }

                if (acc.getBalance() < amount) {
                    System.out.println("Insufficient balance. Withdrawal failed.");
                    return false;
                }

                acc.setBalance(acc.getBalance() - amount);
                System.out.println("Withdrawal successful. New balance: " + acc.getBalance());
                return true;
            }
        }

        System.out.println("Account does not exist.");
        return false;
    }

    @Override
    public boolean loginAccount(Account account) {
        System.out.println("Attempting to log in: " + account.getUserName());

        for (Account acc : accounts) {
            System.out.println("Checking account: " + acc.getUserName());
            if (acc.getUserName().equals(account.getUserName()) &&
                    acc.getPassword().equals(account.getPassword())) {
                return true;
            }
        }

        return false;
    }
    @Override
    public boolean transfer(Account sender, Account recipient, double amount) {
        if (sender.getBalance() < amount) {
            return false;
        }

        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);

        return true;
    }
    @Override
    public Account findAccountByUsername(String username) {
        for (Account acc : accounts) {
            if (acc.getUserName().equals(username)) {
                return acc;
            }
        }
        return null;
    }


}
