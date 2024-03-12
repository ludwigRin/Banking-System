public class Account {
    private final int accountNumber;
    private String accountType;
    private double accountBalance;
    private User user;


    // constructor
    public Account(int accountNr, String type) {
        accountNumber = accountNr;
        accountType = type;
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

    // deposit
    public void deposit(double amount) {
        accountBalance += amount;
    }

    // withdraw
    public void withdraw(double amount) {
        accountBalance -= amount;
    }


}
