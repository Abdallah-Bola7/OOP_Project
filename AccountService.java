package service;


import model.Account;

public interface AccountService {

    boolean createAccount(Account account);
    boolean loginAccount(Account account);
    void showAllAccounts();

    // TODO create function with name deposit that return
    boolean deposit(Account account, double amount);
    boolean withdraw(Account account, double amount);

}
