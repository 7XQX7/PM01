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

    // Getter 和 Setter 方法
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    // 抽象方法，用于区分收入和支出
    public abstract boolean isIncome();
}