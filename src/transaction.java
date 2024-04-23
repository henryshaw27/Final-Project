import java.io.Serializable;
//transaction class for elements of transaction
public class transaction implements Serializable {
    private double amount;
    private double balance;
    public String transType, name;
    public Date date;
    public transaction(String transType, String name, double amount, Date dateTime, double balance) {
        this.transType = transType;
        this.name = name;
        this.amount = amount;
        this.date = dateTime;
        this.balance = balance;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public String getTransType() {
        return transType;
    }
    public double getBalance() { return balance;}
}


