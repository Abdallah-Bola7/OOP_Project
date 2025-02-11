package model;

public class Account {
    private String userName;
    private String password;
    private double balance;
    private boolean active;

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.balance = 0;
        this.active = true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUserName() { return userName; }
    public String getPassword() { return password; }
    public double getBalance() { return balance; }
    public boolean isActive() { return active; }

    public void deposit(double amount) { balance += amount; }
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}