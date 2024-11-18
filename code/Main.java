import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BillManager billManager = new BillManager();

        // 添加收入
        billManager.addIncome(new Date(), 5000, "工资", "本月工资");

        // 添加支出
        billManager.addExpense(new Date(), 1000, "餐饮", "午餐费用");

        // 查询所有交易
        List<Transaction> allTransactions = billManager.getAllTransactions();
        for (Transaction transaction : allTransactions) {
            System.out.println(transaction.getDate() + ", " + transaction.getAmount() + ", " + transaction.getCategory() + ", " + transaction.getRemark());
        }

        // 按类别统计
        double totalSalary = billManager.getTotalByCategory("工资");
        System.out.println("工资总计: " + totalSalary);

        double totalFood = billManager.getTotalByCategory("餐饮");
        System.out.println("餐饮总计: " + totalFood);
    }
}