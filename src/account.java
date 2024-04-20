public class account {
    LinkedList transactionLog;
    double balance;
    String accName, type;
    public account(String accName, String type, double balance, LinkedList transactionLog) {
        this.accName = accName;
        this.type = type;
        this.balance = balance;
        this.transactionLog = transactionLog;
    }

    public double getBalance() {
        return balance;
    }

    public LinkedList getTransactionLog() {
        return transactionLog;
    }

    public String getAccName() {
        return accName;
    }
    public String getType() {
        return type;
    }
}
