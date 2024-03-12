public class Transaction {
    int transactionId;
    int timeStamp;
    double amount;
    String transactionType;

    // constructor
    public Transaction (int transactionId, int timeStamp, double amount, String transactionType) {
        this.transactionId = transactionId;
        this.timeStamp = timeStamp;
        this.amount = amount;
        this.transactionType = transactionType;
    }


}
