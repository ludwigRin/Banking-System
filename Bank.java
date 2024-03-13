import java.util.ArrayList;
import java.util.Stack;

public class Bank {
    
    private ArrayList<User> allUsers = new ArrayList<>();
    private ArrayList<Account> allAccounts = new ArrayList<>();
    private Stack<Transaction> allTransactions = new Stack<>();

    public User createUser(String userName, String password) {

        User newUser = new User(allUsers.size(), userName, password);

        return newUser;
    }

    public Account createAccount(String type, User user) {

        Account newAccount = new Account(allAccounts.size(), type);

        return newAccount;
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
}