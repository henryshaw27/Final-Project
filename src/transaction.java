public class transaction {
    private double amount;
    public String transType, name;
    public Date date;
    public transaction(String transType, String name, double amount, Date dateTime) {
        this.transType = transType;
        this.name = name;
        this.amount = amount;
        this.date = dateTime;
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
}

