import java.util.Date;

public abstract class Transaction {
    private Date date;
    private double amount;
    private String category;
    private String remark;

    public Transaction(Date date, double amount, String category, String remark) {
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.remark = remark;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getRemark() {
        return remark;
    }

    public abstract boolean isIncome();
}