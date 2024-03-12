import java.util.Scanner;
import java.util.ArrayList;


class User {
    private final int userId;
    private String userName;
    private String password;
    //private double balance;
    private ArrayList<Account> accounts;

    // constructor
    public User(int id, String name, String userPassword) {
        userId = id;
        userName = name;
        password = userPassword;
        accounts = new ArrayList<>();
    }

    // getters
    public int getId() {
        return userId;
    }
    public String getName() {
        return userName;
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
        this.userName = newName;
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
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

    
    public void changePassword() {
        Scanner in = new Scanner(System.in);

        // check current password
        System.out.print("Enter your current password: ");
        String oldPassword = in.nextLine();
        
        // validate current password
        if (oldPassword.equals(password)) {
            System.out.print("Please enter a new password: ");
            String newPassword = in.nextLine();
            System.out.print("Please enter your new password again: ");
            String validateNewPassword = in.nextLine();
            
            // change password
            if (newPassword.equals(validateNewPassword)) {
                setPassword(newPassword);
                System.out.println("Password changed successfully!");
            }
        } else {
            System.out.println("Wrong password!");
        }
    }
}