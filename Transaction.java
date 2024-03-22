import java.security.Timestamp;

public class Transaction {
    int transactionId;
    Timestamp timeStamp;
    double amount;
    String transactionType;
    Account recipient;
    Account owner;



    // constructor
    public Transaction (Bank bank, Timestamp timeStamp, double amount, String transactionType, Account owner) {
        this.transactionId = bank.getAllTransactions().size();
        this.timeStamp = timeStamp;
        this.amount = amount;
        this.transactionType = transactionType;
        this.owner = owner;
    }


    public Transaction (Bank bank, Timestamp timeStamp, double amount, String transactionType, Account owner, Account recipient) {
        this(bank, timeStamp, amount, transactionType, owner);

        this.recipient = recipient;
    }


    public void execute() {

        owner.setBalance(owner.getBalance() + this.amount);
        owner.addTransaction(this);

        if(recipient != null){
            recipient.setBalance(recipient.getBalance() - this.amount);
            recipient.addTransaction(this);
        }

    }
}