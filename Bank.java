import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.time.LocalDateTime;

public class Bank { 
    String bankName;
    private ArrayList<User> allUsers = new ArrayList<>();
    private ArrayList<Account> allAccounts = new ArrayList<>();
    private Stack<Transaction> allTransactions = new Stack<>();
    User activeUser = null;

    public Bank (String bankName) {
        this.bankName = bankName;
    }

    public User createUser(String username, String password) {

        User newUser = new User(allUsers.size(), username, password);

        allUsers.add(newUser);

        return newUser;
    }

    public Account createAccount(String type, User activUser) {

        Account newAccount = new Account(allAccounts.size(), type);

        allAccounts.add(newAccount);
        activUser.addAccount(newAccount);

        return newAccount;
    }

    public int searchForUser(String username) {
        for (User user : this.getAllUsers()) {
            if (user.getName().equals(username)) {
                return this.getAllUsers().indexOf(user);
            }
        }
        return -1;
    }

    public boolean correctPassword(String username, String password) {
        if (this.getAllUsers().get(this.searchForUser(username)).getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    // setter
    public void addTransaction(Transaction transaction) {

        this.allTransactions.add(transaction);
    }



    // getter
    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public ArrayList<Account> getAllAccounts() {
        return allAccounts;
    }
    
    public Stack<Transaction> getAllTransactions() {
        return allTransactions;
    }



    public void deleteUser(User user) {

        // delete all Accounts connected to the user
        user.deleteAccounts();
        // delete the User
        user = null;
    }

    public void deleteAccount(Account account) {

        // delete Account
        account = null;
    }

    public boolean bankLogIn(Scanner in, Bank bank, User activeUser) {
        System.out.print("Please enter your username: ");
        String inputUsername = in.nextLine();
        System.out.print("Please enter your password: ");
        String inputPassword = in.nextLine();
        if (bank.correctPassword(inputUsername, inputPassword)) {
            this.activeUser = bank.getAllUsers().get(bank.searchForUser(inputUsername));
            return true;
        } else {
            System.out.println("Wrong password or username!");
            return false;
        }
    }

    public User setUser() {
        return this.activeUser;
    }

    public int mainMenuSelect(Scanner in, Bank bank) {
        System.out.println("Welcome to " + bank.bankName + "!");
        System.out.println("How can i help you?");
        System.out.println("1. Log in");
        System.out.println("2. Create an Account");
        System.out.println("0. Exit");
        while (!in.hasNextInt()) {
            System.out.println("Please enter an integer from 0 to 2!");
            in.next();
        }
        int choice = in.nextInt();
        in.nextLine();
        if (choice == 1) return 2;
        if (choice == 2) return 3;
        if (choice == 0) return 14;
        return 14;
    }

    public int accountCreateOrSelectButton(Scanner in) {
        System.out.println("How would you like to proceed?");
        System.out.println("1. Select existing bank account");
        System.out.println("0. Create a new bank account");
        while (!in.hasNextInt()) {
            System.out.println("Please enter an integer!");
            in.next();
        }
        int choice = in.nextInt();
        in.nextLine();
        if (choice == 1) return 5;
        if (choice == 0) return 6;
        return 14;
    }

    public Account accountSelection(Scanner in, User activeUser, int selectedAccountIndex, Account selectedAccount) {
        activeUser.printAccounts();
        System.out.print("Please select the account you want to use: ");
        while (!in.hasNextInt()) {
            System.out.println("Please enter an integer!");
            in.next();
        }
        selectedAccountIndex = in.nextInt();
        in.nextLine();
        selectedAccount = activeUser.getAccounts().get(selectedAccountIndex - 1);
        return selectedAccount;
    }

    public void userCreation(Scanner in, Bank bank) {
        System.out.print("Please enter a username: ");
        String newUsername = in.nextLine();
        if (bank.searchForUser(newUsername) != -1) {
            System.out.println("The username already exists!");
        }
        System.out.print("Please enter a password: ");
        String newPassword = in.nextLine();
        System.out.print("Please enter the password again: ");
        String validateNewPassword = in.nextLine();
        if (newPassword.equals(validateNewPassword)) {
            bank.createUser(newUsername, newPassword);
            System.out.println("Your Account has been created");
        } else {
            System.out.println("The passwords entered didn't match!");
        }
    }

    public void accountCreation(Scanner in, User activeUser, Bank bank) {
        System.out.println("What type of bank account would you like to create?");
        System.out.println("1. Basic");
        System.out.println("2. Savings");
        while (!in.hasNextInt()) {
            System.out.println("Please enter an integer!");
            in.next();
        }
        int selection = in.nextInt();
        String type = (selection < 2) ? "Basic" : "Savings";
        bank.createAccount(type, activeUser);
    }

    public int accountMenu(Scanner in, User activeUser, Account selectedAccount, int selectedAccountIndex) {
        System.out.println("Welcome " + activeUser.getName());
        System.out.println("What would you like to do?");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Transfer");
        System.out.println("4. Check Balance");
        System.out.println("5. Transaction History");
        System.out.println("0. Log Out");
        while (!in.hasNextInt()) {
            System.out.println("Please enter an integer from 0 to 5!");
            in.next();
        }
        int choice = in.nextInt();
        in.nextLine();
        if (choice == 1) return 8;
        if (choice == 2) return 9;
        if (choice == 3) return 10;
        if (choice == 4) return 11;
        if (choice == 5) return 12;
        if (choice == 0) return 13;
        return 14;
    }

    public void accountWithdraw(Scanner in, Bank bank, Account selectedAccount) {
        System.out.print("Please enter the amount you would like to withdraw: ");
        double amountWithdraw = in.nextDouble();
        in.nextLine();
        if (amountWithdraw < selectedAccount.getBalance()) {
            Transaction withdraw = new Transaction(bank, LocalDateTime.now(), -amountWithdraw, "Withdraw", selectedAccount);
            withdraw.execute();
            System.out.println("You successfully withdrew " + amountWithdraw + " Euro");
            System.out.println("Your new balance is " + selectedAccount.getBalance());
        } else {
            System.out.println("You dont have enough money :(");
        }
    }

    public void accountDeposit(Scanner in, Bank bank, Account selectedAccount ) {
        System.out.print("Please enter the amount you would like to depsoit: ");
        double amountDeposit = in.nextDouble();
        in.nextLine();
        Transaction deposit = new Transaction(bank, LocalDateTime.now(), amountDeposit, "Deposit", selectedAccount);
        deposit.execute();
        System.out.println("You successfully deposited " + amountDeposit + " Euro");
        System.out.println("Your new balance is " + selectedAccount.getBalance() + " Euro");
    }

    public void accountTransfer(Scanner in, Bank bank, Account selectedAccount) {
        System.out.print("Please enter the Account ID of the recipient: ");
        while (!in.hasNextInt()) {
            System.out.println("Please enter the Account ID");
            in.next();
        }
        int accountID = in.nextInt();
        boolean accountExists = false;
        Account recipient = null;
        for (Account i : this.allAccounts) {
            if (i.getAccountNumber() == accountID) {
                recipient = i;
                accountExists = true;
            }
        }
        if (accountExists) {
        
        System.out.print("Please enter the amount you would like to transfer: ");
        double amountTransfer = in.nextDouble();
        in.nextLine();
        if (amountTransfer < selectedAccount.getBalance()) {
            Transaction transfer = new Transaction(bank, LocalDateTime.now(), -amountTransfer, "Transfer", selectedAccount, recipient);
            transfer.execute();
            System.out.println("You successfully transferred " + amountTransfer + " Euro");
            System.out.println("Your new balance is " + selectedAccount.getBalance() + " Euro");
        } else if (amountTransfer > selectedAccount.getBalance()) {
            System.out.println("You dont have enough money :(");
        }
        } else {
            System.out.println("Couldn't find any account with this ID");
        }
    }

    public void accountGetBalance(Account selectedAccount) {
        System.out.println("Your current balance is " + selectedAccount.getBalance() + " Euro");
    }

    public void accountGetHistory(Account selectedAccount) {
        for (Transaction i : selectedAccount.getTransactions()) {
            System.out.println("***********************");
            System.out.println(i.transactionType);
            System.out.println(i.timeStamp);
            System.out.println("Transaction ID: " + i.transactionId);
            System.out.println("Amount: " + i.amount);
            if (i.transactionType.equals("Transfer")) {
                System.out.println("Recipient Account ID: " + i.recipient.getAccountNumber());
            }
            System.out.println("***********************");
        }
    }

    public void userLogout(User activeUser, Account selecetedAccount, int selectedAccountIndex) {
        activeUser = null;
        selecetedAccount = null;
        selectedAccountIndex = 0;
    }
}