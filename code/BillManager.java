import java.util.*;
import java.util.stream.Collectors;

public class BillManager {
    private List<Transaction> transactions;
    private double monthlyBudget;
    private double remainingBudget;

    public BillManager() {
        transactions = new ArrayList<>();
        this.monthlyBudget = 0;
        this.remainingBudget = 0;
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
        updateRemainingBudget(amount); // 更新剩余预算
    }

    // 设置月度预算
    public void setMonthlyBudget(double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
        this.remainingBudget = monthlyBudget; // 重置剩余预算
    }

    // 获取剩余预算
    public double getRemainingBudget() {
        return remainingBudget;
    }

    // 更新剩余预算
    private void updateRemainingBudget(double amount) {
        remainingBudget -= amount; // 支出减少预算
    }

    // 获取所有交易
    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    // 获取所有收入
    public List<Income> showAllIncomes() {
        return transactions.stream()
                .filter(t -> t instanceof Income)
                .map(t -> (Income) t)
                .collect(Collectors.toList());
    }

    // 获取所有支出
    public List<Expense> showAllExpenses() {
        return transactions.stream()
                .filter(t -> t instanceof Expense)
                .map(t -> (Expense) t)
                .collect(Collectors.toList());
    }

    // 月度统计
    public Map<String, Double> getMonthlyStatistics(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);

        return transactions.stream()
                .filter(t -> {
                    cal.setTime(t.getDate());
                    return cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) == month;
                })
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.summingDouble(Transaction::getAmount)
                ));
    }

    // 获取月度总收入
    public double getMonthlyTotalIncome(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);

        return transactions.stream()
                .filter(t -> t instanceof Income)
                .filter(t -> {
                    cal.setTime(t.getDate());
                    return cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) == month;
                })
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    // 获取月度总支出
    public double getMonthlyTotalExpense(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);

        return transactions.stream()
                .filter(t -> t instanceof Expense)
                .filter(t -> {
                    cal.setTime(t.getDate());
                    return cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) == month;
                })
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
    public List<Transaction> queryByDateRange(Date startDate, Date endDate) {
        return transactions.stream()
                .filter(t -> !t.getDate().before(startDate) && !t.getDate().after(endDate))
                .collect(Collectors.toList());
    }
}