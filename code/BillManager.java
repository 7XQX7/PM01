import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillManager {
    private List<Transaction> transactions;

    public BillManager() {
        transactions = new ArrayList<>();
    }

    // 添加收入
    public void addIncome(Date date, double amount, String category, String remark) {
        if (amount <= 0) {
            throw new IllegalArgumentException("收入金额必须为正数");
        }
        transactions.add(new Income(date, amount, category, remark));
    }

    // 添加支出
    public void addExpense(Date date, double amount, String category, String remark) {
        if (amount <= 0) {
            throw new IllegalArgumentException("支出金额必须为正数");
        }
        transactions.add(new Expense(date, amount, category, remark));
    }

    // 查询所有交易
    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    // 按类别统计
    public double getTotalByCategory(String category) {
        double total = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getCategory().equals(category)) {
                if (transaction.isIncome()) {
                    total += transaction.getAmount();
                } else {
                    total -= transaction.getAmount();
                }
            }
        }
        return total;
    }

    // 其他查询和统计方法可以根据需要添加
}