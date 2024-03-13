import java.util.ArrayList;


public class Account {
    private final int accountNumber;
    private String accountType;
    private double accountBalance;
    private User user;
    private ArrayList<Transaction> transactions;


    // constructor
    public Account(int accountNr, String type) {
        accountNumber = accountNr;
        accountType = type;
        transactions = new ArrayList<>();
    }

    public Account(int accountNr, String type, User user) {
        this(accountNr, type); 
        
        
        if (!this.hasUser()){
            this.user = user;
            user.addAccount(this);

        } else{
            System.out.println("Can't add a second user to account");
        }
    }

    public boolean hasUser(){
        return (user != null);
    }

    // getter
    public int getAccountNumber() {
        return accountNumber;
    }
    public String getAccountType() {
        return accountType;
    }
    public double getBalance() {
        return accountBalance;
    }

    // setter
    public void setAccountType(String newType) {
        this.accountType = newType;
    }
    public void setBalance(double newBalance) {
        this.accountBalance = newBalance;
    }
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    // deposit
    public void deposit(double amount) {
        accountBalance += amount;
    }

    // withdraw
    public void withdraw(double amount) {
        accountBalance -= amount;
    }

    // transfer
    public void transfer(Account sender, Account recipient, Double amount) {
        sender.accountBalance -= amount;
        recipient.accountBalance += amount;
    }
}
