package model;

import java.util.ArrayList;
import java.util.List;


// pls apply singleton
public class Ewallet {
    private static Ewallet instance;
    private final String name = "EraaSoft Cash";
    private final List<Account> accounts = new ArrayList<>();

    private Ewallet() {}

    public static synchronized Ewallet getInstance() {
        if (instance == null) {
            instance = new Ewallet();
        }
        return instance;
    }

    public List<Account> getAccounts() { return accounts; }
    public void addAccount(Account account) { accounts.add(account); }
}
