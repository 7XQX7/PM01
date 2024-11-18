import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BillManager billManager = new BillManager();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // 设置月度预算
            System.out.println("请输入月度预算限额：");
            double monthlyBudget = scanner.nextDouble();
            billManager.setMonthlyBudget(monthlyBudget);
            scanner.nextLine(); // 清除换行符

            // 添加收入
            System.out.println("请输入收入的日期（格式：yyyy-MM-dd）：");
            String dateStr = scanner.nextLine();
            Date date = dateFormat.parse(dateStr);
            System.out.println("请输入收入的金额：");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // 清除换行符
            System.out.println("请输入收入的类别：");
            String category = scanner.nextLine();
            System.out.println("请输入收入的备注：");
            String remark = scanner.nextLine();

            billManager.addIncome(date, amount, category, remark);

            // 添加支出
            System.out.println("请输入支出的日期（格式：yyyy-MM-dd）：");
            dateStr = scanner.nextLine();
            date = dateFormat.parse(dateStr);
            System.out.println("请输入支出的金额：");
            amount = scanner.nextDouble();
            scanner.nextLine(); // 清除换行符
            System.out.println("请输入支出的类别：");
            category = scanner.nextLine();
            System.out.println("请输入支出的备注：");
            remark = scanner.nextLine();

            billManager.addExpense(date, amount, category, remark);

            // 展示所有收入
            List<Income> incomes = billManager.showAllIncomes();
            System.out.println("所有收入:");
            for (Income income : incomes) {
                System.out.println(income.getDate() + ", " + income.getAmount() + ", " + income.getCategory() + ", " + income.getRemark());
            }

            // 展示所有支出
            List<Expense> expenses = billManager.showAllExpenses();
            System.out.println("\n所有支出:");
            for (Expense expense : expenses) {
                System.out.println(expense.getDate() + ", " + expense.getAmount() + ", " + expense.getCategory() + ", " + expense.getRemark());
            }

            // 显示剩余预算
            System.out.println("\n当前剩余可用预算：" + billManager.getRemainingBudget());

            // 月度统计
            Map<String, Double> statistics = billManager.getMonthlyStatistics(date);
            System.out.println("\n月度统计：");
            for (Map.Entry<String, Double> entry : statistics.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            // 月度总收入和总支出
            double totalIncome = billManager.getMonthlyTotalIncome(date);
            double totalExpense = billManager.getMonthlyTotalExpense(date);
            System.out.println("\n月度总收入：" + totalIncome);
            System.out.println("月度总支出：" + totalExpense);

        } catch (ParseException e) {
            System.out.println("日期格式错误，请输入正确的日期格式（yyyy-MM-dd）");
        } finally {
            // 关闭scanner
            scanner.close();
        }
    }
}