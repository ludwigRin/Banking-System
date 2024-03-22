import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Bank { 
    String bankName;
    private ArrayList<User> allUsers = new ArrayList<>();
    private ArrayList<Account> allAccounts = new ArrayList<>();
    private Stack<Transaction> allTransactions = new Stack<>();

    public Bank (String bankName) {
        this.bankName = bankName;
    }

    public User createUser(String username, String password) {

        User newUser = new User(allUsers.size(), username, password);

        allUsers.add(newUser);

        return newUser;
    }

    public Account createAccount(String type, User user) {

        Account newAccount = new Account(allAccounts.size(), type);

        allAccounts.add(newAccount);
        user.addAccount(newAccount);

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
            activeUser = bank.getAllUsers().get(bank.searchForUser(inputUsername));
            return true;
        } else {
            System.out.println("Wrong password or username!");
            return false;
        }
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
                return choice;
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
}