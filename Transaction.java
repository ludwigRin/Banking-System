public class Transaction {
    int transactionId;
    int timeStamp;
    double amount;
    String transactionType;
    Account recipient;
    Account sender;

    // constructor
    public Transaction (int transactionId, int timeStamp, double amount, String transactionType, Account recipient) {
        this.transactionId = transactionId;
        this.timeStamp = timeStamp;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public Transaction (int transactionId, int timeStamp, double amount, String transactionType, Account recipient, Account sender) {
        this(transactionId, timeStamp, amount, transactionType, recipient);

        this.sender = sender;
    }
}