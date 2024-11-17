import java.util.Date;

public class Income extends Transaction {
    public Income(Date date, double amount, String category, String remark) {
        super(date, amount, category, remark);
    }

    @Override
    public boolean isIncome() {
        return true;
    }
}