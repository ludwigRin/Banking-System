import java.util.Scanner;
import java.util.ArrayList;


class User {
    private final int userId;
    private String username;
    private String password;
    private ArrayList<Account> accounts;

    // constructor
    public User(int id, String name, String userPassword) {
        userId = id;
        username = name;
        password = userPassword;
        // TODO fix this
        accounts = new ArrayList<>();
    }

    // getters
    public int getId() {
        return userId;
    }
    public String getName() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public double getBalance() {
        double result = 0;
        for(int i = 0; i < accounts.size(); i++){
            result += this.accounts.get(i).getBalance();
        }
        return result;
    }
    public ArrayList<Account> getAccounts() {
        return this.accounts;
    }

    

    // setters
    public void setName(String newName) {
        this.username = newName;
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    
    public void deleteAccounts() {
        this.accounts = null;
    
    }

    public void addAccount(Account account) {
        boolean accountNotAlreadyAssigned = true;
        for (Account i : accounts) {
            if (account == i) {
                accountNotAlreadyAssigned = false;
            }
        }
        if (accountNotAlreadyAssigned) {
            this.accounts.add(account);
        } else {
            System.out.println("This account is already assigned to the user");
        }
        
    }

    public void printAccounts() {
        int counter = 1;
        for (Account i : accounts) {
            System.out.println(counter + ".");
            System.out.println(" Account ID: " + i.getAccountNumber());
            System.out.println(" Type: " + i.getAccountType());
            System.out.println(" Balance: " + i.getBalance());
            System.out.println("");
            counter += 1;
        }
    } 

    public boolean bankLogIn(Scanner in, Bank newBank, User activeUser) {
        System.out.print("Please enter your username: ");
        String inputUsername = in.nextLine();
        System.out.print("Please enter your password: ");
        String inputPassword = in.nextLine();
        if (newBank.correctPassword(inputUsername, inputPassword)) {
            activeUser = newBank.getAllUsers().get(newBank.searchForUser(inputUsername));
            return true;
        } else {
            System.out.println("Wrong password or username!");
            return false;
        }
    }
}