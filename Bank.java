import java.util.ArrayList;

public class Bank {
    
    private ArrayList<Account> allUsers = new ArrayList<>();
    private ArrayList<Account> allAccounts = new ArrayList<>();

    public User createUser(String userName, String password) {

        // creating new User
        User newUser = new User(allUsers.size(), userName, password);

        return newUser;
    }

    public Account createAccount(String type, User user) {

        // creating new Account
        Account newAccount = new Account(allAccounts.size(), type);

        return newAccount;
    }

    public void deleteUser(User user) {

        // delets all Accounts connected to the user
        user.setAccounts(null);
        // delets the User
        user = null;
    }

    public void deleteAccount(Account account) {

        // deletes Account
        account = null;
    }
}