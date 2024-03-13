import java.security.Timestamp;

public class Transaction {
    int transactionId;
    Timestamp timeStamp;
    double amount;
    String transactionType;
    Account recipient;
    Account owner;

    // constructor
    public Transaction (int transactionId, Timestamp timeStamp, double amount, String transactionType, Account owner) {
        this.transactionId = transactionId;
        this.timeStamp = timeStamp;
        this.amount = amount;
        this.transactionType = transactionType;
        this.owner = owner;
    }

    public Transaction (int transactionId, Timestamp timeStamp, double amount, String transactionType, Account owner, Account recipient) {
        this(transactionId, timeStamp, amount, transactionType, owner);

        this.recipient = recipient;
    }


    public void execute() {

        owner.setBalance(owner.getBalance() + this.amount);

        if(recipient != null){
            recipient.setBalance(recipient.getBalance() - this.amount);
        }
    }
}