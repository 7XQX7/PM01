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

        while (true) {
            System.out.println("# 欢迎使用个人账单管理系统");
            System.out.println("# 请选择操作：");
            System.out.println("1. 记录收入");
            System.out.println("2. 记录支出");
            System.out.println("3. 查看所有账单");
            System.out.println("4. 查询账单");
            System.out.println("5. 设置月度预算");
            System.out.println("6. 查看月度统计报告");
            System.out.println("7. 退出系统");
            System.out.print("请输入选项序号：");

            int option = scanner.nextInt();
            scanner.nextLine(); // 清除换行符

            switch (option) {
                case 1:
                    recordIncome(scanner, dateFormat, billManager);
                    break;
                case 2:
                    recordExpense(scanner, dateFormat, billManager);
                    break;
                case 3:
                    viewAllBills(billManager);
                    break;
                case 4:
                    queryBills(scanner, dateFormat, billManager);
                    break;
                case 5:
                    setMonthlyBudget(scanner, billManager);
                    break;
                case 6:
                    viewMonthlyStatistics(scanner, dateFormat, billManager);
                    break;
                case 7:
                    System.out.println("感谢使用个人账单管理系统，再见！");
                    return;
                default:
                    System.out.println("无效的选项，请重新选择。");
                    break;
            }

            System.out.println("\n按任意键返回主菜单...");
            scanner.nextLine(); // 等待用户按下回车键
        }
    }

    private static void recordIncome(Scanner scanner, SimpleDateFormat dateFormat, BillManager billManager) {
        System.out.println("请输入收入信息：");
        System.out.print("日期（YYYY-MM-DD）：");
        String dateStr = scanner.nextLine();
        System.out.print("金额：");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // 清除换行符
        System.out.print("类别（如工资、奖金等）：");
        String category = scanner.nextLine();
        System.out.print("备注：");
        String remark = scanner.nextLine();

        try {
            Date date = dateFormat.parse(dateStr);
            billManager.addIncome(date, amount, category, remark);
            System.out.println("收入已成功记录！");
        } catch (ParseException e) {
            System.out.println("日期格式错误，请输入正确的日期格式（yyyy-MM-dd）");
        }
    }

    private static void recordExpense(Scanner scanner, SimpleDateFormat dateFormat, BillManager billManager) {
        System.out.println("请输入支出信息：");
        System.out.print("日期（YYYY-MM-DD）：");
        String dateStr = scanner.nextLine();
        System.out.print("金额：");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // 清除换行符
        System.out.print("类别（如餐饮、交通等）：");
        String category = scanner.nextLine();
        System.out.print("备注：");
        String remark = scanner.nextLine();

        try {
            Date date = dateFormat.parse(dateStr);
            billManager.addExpense(date, amount, category, remark);
            System.out.println("支出已成功记录！");
        } catch (ParseException e) {
            System.out.println("日期格式错误，请输入正确的日期格式（yyyy-MM-dd）");
        }
    }

    private static void viewAllBills(BillManager billManager) {
        System.out.println("所有账单：");
        List<Income> incomes = billManager.showAllIncomes();
        for (Transaction income : incomes) {
            System.out.println(income.getDate() + ", " + income.getAmount() + ", " + income.getCategory() + ", " + income.getRemark());
        }

        List<Expense> expenses = billManager.showAllExpenses();
        for (Transaction expense : expenses) {
            System.out.println(expense.getDate() + ", " + expense.getAmount() + ", " + expense.getCategory() + ", " + expense.getRemark());
        }
    }

    private static void queryBills(Scanner scanner, SimpleDateFormat dateFormat, BillManager billManager) {
        System.out.println("请输入查询条件：");
        System.out.print("开始日期（YYYY-MM-DD）：");
        String startDateStr = scanner.nextLine();
        System.out.print("结束日期（YYYY-MM-DD）：");
        String endDateStr = scanner.nextLine();

        try {
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);
            List<Transaction> transactions = billManager.queryByDateRange(startDate, endDate);
            System.out.println("查询结果：");
            for (Transaction transaction : transactions) {
                System.out.println(transaction.getDate() + ", " + transaction.getAmount() + ", " + transaction.getCategory() + ", " + transaction.getRemark());
            }
        } catch (ParseException e) {
            System.out.println("日期格式错误，请输入正确的日期格式（yyyy-MM-dd）");
        }
    }

    private static void setMonthlyBudget(Scanner scanner, BillManager billManager) {
        System.out.println("请输入月度预算限额：");
        double monthlyBudget = scanner.nextDouble();
        billManager.setMonthlyBudget(monthlyBudget);
        System.out.println("月度预算已设置为：" + monthlyBudget);
    }

    private static void viewMonthlyStatistics(Scanner scanner, SimpleDateFormat dateFormat, BillManager billManager) {
        System.out.println("请输入查询月份的日期（YYYY-MM-DD）：");
        String dateStr = scanner.nextLine();

        try {
            Date date = dateFormat.parse(dateStr);
            Map<String, Double> statistics = billManager.getMonthlyStatistics(date);
            System.out.println("月度统计报告：");
            for (Map.Entry<String, Double> entry : statistics.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            double totalIncome = billManager.getMonthlyTotalIncome(date);
            double totalExpense = billManager.getMonthlyTotalExpense(date);
            System.out.println("月度总收入：" + totalIncome);
            System.out.println("月度总支出：" + totalExpense);
        } catch (ParseException e) {
            System.out.println("日期格式错误，请输入正确的日期格式（yyyy-MM-dd）");
        }
    }
}