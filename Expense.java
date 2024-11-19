import java.util.Date;

public class Expense extends Transaction {
    public Expense(Date date, double amount, String category, String remark) {
        super(date, amount, category, remark);
    }

    @Override
    public boolean isIncome() {
        return false;
    }
}