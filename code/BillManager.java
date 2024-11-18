import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BillManager {
    private List<Transaction> transactions;

    public BillManager() {
        transactions = new ArrayList<>();
    }

    public void addIncome(Date date, double amount, String category, String remark) {
        if (amount <= 0) {
            throw new IllegalArgumentException("收入金额必须为正数");
        }
        transactions.add(new Income(date, amount, category, remark));
    }

    public void addExpense(Date date, double amount, String category, String remark) {
        if (amount <= 0) {
            throw new IllegalArgumentException("支出金额必须为正数");
        }
        transactions.add(new Expense(date, amount, category, remark));
    }

    public List<Transaction> showAllIncomes() {
        return transactions.stream()
                .filter(Transaction::isIncome)
                .collect(Collectors.toList());
    }

    public List<Transaction> showAllExpenses() {
        return transactions.stream()
                .filter(t -> !t.isIncome())
                .collect(Collectors.toList());
    }

    public List<Transaction> queryByDate(Date date) {
        return transactions.stream()
                .filter(t -> t.getDate().equals(date))
                .collect(Collectors.toList());
    }

    public List<Transaction> queryByDateRange(Date startDate, Date endDate) {
        return transactions.stream()
                .filter(t -> !t.getDate().before(startDate) && !t.getDate().after(endDate))
                .collect(Collectors.toList());
    }

    public List<Transaction> queryByCategory(String category) {
        return transactions.stream()
                .filter(t -> t.getCategory().equals(category))
                .collect(Collectors.toList());
    }
}