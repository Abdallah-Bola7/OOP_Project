package service;

import model.Account;

import java.util.Scanner;



public class ApplicationServiceImpl implements ApplicationService{

    private Account loggedInAccount = null;


    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome Sir");

        // 1.TODO please apply mulite choose for 4 times


        boolean out=false;
        int tries=0;
        while(tries<4 && !out){
            System.out.println("Please Enter your choose");
            System.out.println("a.login     b.signup   c.show accounts    d.exit");
            char choose = scanner.next().charAt(0);
            switch (choose) {
                case 'a':
                    login();
                    out =true;
                    break;
                case 'b':
                    signup();
                    out =true;
                    run();
                    break;
                case 'c':
                    AccountService accountService = new AccountServiceImpl();
                    accountService.showAllAccounts();
                    break;
                case 'd':
                    System.out.println("you are welcome.");
                    out =true;
                    break;

                default:
                    tries++;
                    System.out.println("Invalid choice! You have " + (4 - tries) + " try(s) left.");
                    break;
            }

        }
        if (tries == 4) {
            System.out.println("Sorry, you've used all your tries , Try again later.");
        }
        scanner.close();

    }

    private void signup() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please Enter User name");
        String name = scanner.nextLine();

        System.out.println("Please Enter password");
        String password = scanner.nextLine();

        ValidationService validationService = new ValidationServiceImpl();

        // 2.TODO Validation on UserName and Password
        if (!validationService.validateUserName(name)) { // "eslam"
            System.out.println("Invalid UserName");
            return;
        }

        if (!validationService.validatePassword(password)) {
            System.out.println("Invalid Password");
            return;
        }


        // 3.TODO SERVICE OF ACCOUNT TO CREATE ACCOUNT



        AccountService accountService = new AccountServiceImpl();
        Account account = new Account(name, password);
        account.setActive(true);

        // 4.TODO   impl createAccount
        boolean isAccountCreated = accountService.createAccount(account);
        if (isAccountCreated) {
            System.out.println("Account Created");
        } else {
            System.out.println("Account not Created Because There exist account with same user name");
        }

    }



    private void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please Enter User name");
        String name = scanner.nextLine();

        System.out.println("Please Enter password");
        String password = scanner.nextLine();

        ValidationService validationService = new ValidationServiceImpl();

        if (!validationService.validateUserName(name)) {
            System.out.println("Invalid UserName");
            return;
        }

        if (!validationService.validatePassword(password)) {
            System.out.println("Invalid Password");
            return;
        }

        AccountService accountService = new AccountServiceImpl();
        Account account = new Account(name, password);

        if (accountService.loginAccount(account)) {
            loggedInAccount = account;
            System.out.println("Login Success");
            services();
        } else {
            System.out.println("Account not Exist");
        }

    }

    private void services() {

        System.out.println("1.Deposit   2.Withdraw    3.show details    4.Transfer    5.show balance   6.logout  7.exit");

        // TODO create switch case such as on run function
        Scanner scanner=new Scanner(System.in);
        char choose = scanner.next().charAt(0);
        switch (choose) {
            case '1':
                deposit();
                services();
                break;
            case '2':
                withdraw();
                services();
                break;
            case '3':
                showDetails();
                break;
            case '4':
                //transfer();
                break;
            case '5':
                showBalance();
                break;
            case '6':
                logout();
                break;
            case '7':
                System.out.println("Exiting the application. Goodbye!");
                break;
            default:
                System.out.println("Invalid choice! ");
                break;


            // TODO every case on switch call function  don't forget (Invalid choose)
        }

        // TODO create deposit function


        // TODO create Withdraw function
//        void withdraw (Account a){
//            // input int money
//            // TODO pls validate money >= 100 and <= 8000
//        }
//
//        void showDetails (Account a){
//
//        }

//        void transfer (Account withdrawAccount){
//            // TODO USER MUST give me user name of account that will transfer
//            // TODO input Account depositAccount
//            // TODO input int money
//        }

//        void showBalance (Account a){
//
//        }

    }
    private void deposit() {
        if (loggedInAccount == null) {
            System.out.println("You must be logged in to deposit.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter deposit amount:");

        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            return;
        }

        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }

        loggedInAccount.setBalance(loggedInAccount.getBalance() + amount);
        System.out.println("Deposit successful! New Balance: $" + loggedInAccount.getBalance());
    }

    private void withdraw() {
        if (loggedInAccount == null) {
            System.out.println("You must be logged in to withdraw.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter withdrawal amount:");

        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            return;
        }

        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
            return;
        }

        if (amount > loggedInAccount.getBalance()) {
            System.out.println("Insufficient balance! Your balance is: $" + loggedInAccount.getBalance());
            return;
        }

        // ✅ Deduct amount from balance
        loggedInAccount.setBalance(loggedInAccount.getBalance() - amount);
        System.out.println("Withdrawal successful! New Balance: $" + loggedInAccount.getBalance());
    }

    private void showDetails() {
        if (loggedInAccount == null) {
            System.out.println("You must be logged in to view account details.");
            return;
        }

        System.out.println("\n===== Account Details =====");
        System.out.println("Username: " + loggedInAccount.getUserName());
        System.out.println("Balance: $" + loggedInAccount.getBalance());
        System.out.println("==========================\n");
    }
    private void showBalance() {
        if (loggedInAccount == null) {
            System.out.println("You must be logged in to check your balance.");
            return;
        }

        System.out.println("\n===== Account Balance =====");
        System.out.println("Current Balance: $" + loggedInAccount.getBalance());
        System.out.println("==========================\n");
    }
    private void logout() {
        if (loggedInAccount == null) {
            System.out.println("You are not logged in.");
            return;
        }

        loggedInAccount = null;
        System.out.println("Logout successful. Returning to main menu...");
        run();
    }







}

