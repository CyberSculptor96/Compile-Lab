package taxcalculator;

import java.util.Scanner;

/**
 * 基于命令行的图形用户界面控制器，用于处理用户输入和显示结果。
 */
public class UIController {
    private Scanner scanner;
    private TaxRateManager taxRateManager;
    private final String adminPassword = "admin123"; // 简单的管理员密码

    /**
     * 构造函数，初始化必要的组件。
     */
    public UIController() {
        this.scanner = new Scanner(System.in);
        this.taxRateManager = TaxRateManager.getInstance();
    }
    
    /**
     * 验证管理员密码。
     * 
     * @return 如果密码正确返回true，否则返回false。
     */
    private boolean verifyAdminAccess() {
        System.out.print("请输入管理员密码以访问此功能：");
        String inputPassword = scanner.next();
        return adminPassword.equals(inputPassword);
    }

    /**
     * 调整税率区间。
     */
    private void adjustTaxRates() {
        if (!verifyAdminAccess()) {
            System.out.println("密码错误，访问被拒绝。");
            return;
        }

        System.out.println("选择要调整的税率区间（输入数字1-7）：");
        int bracketIndex = scanner.nextInt() - 1;
        System.out.print("输入新的区间下限：");
        double lowerBound = scanner.nextDouble();
        System.out.print("输入新的区间上限（输入0表示Double.MAX_VALUE）：");
        double upperBound = scanner.nextDouble();
        upperBound = (upperBound == 0) ? Double.MAX_VALUE : upperBound;
        System.out.print("输入新的税率（例如，0.1表示10%）：");
        double rate = scanner.nextDouble();

        taxRateManager.updateTaxBracket(bracketIndex, lowerBound, upperBound, rate);
        System.out.println("税率已更新。");
    }
    
    /**
     * 调整起征点。
     */
    private void adjustThreshold() {
        if (!verifyAdminAccess()) {
            System.out.println("密码错误，访问被拒绝。");
            return;
        }

        System.out.print("请输入新的起征点：");
        double newThreshold = scanner.nextDouble();
        taxRateManager.updateThreshold(newThreshold);
        System.out.println("起征点已更新。");
    }

    /**
     * 显示主菜单。
     */
    public void showMenu() {
        System.out.println("\n欢迎使用个人所得税计算器");
        System.out.println("1. 计算个人所得税");
        System.out.println("2. 调整税率（管理员功能）");
        System.out.println("3. 调整起征点（管理员功能）");
        System.out.println("4. 退出");
        System.out.print("请选择一个选项：");
    }

    /**
     * 显示主窗口，处理用户选择。
     */
    public void showMainWindow() {
        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            handleUserChoice(choice);
        } while (choice != 4);
    }

    /**
     * 处理用户的选择。
     * 
     * @param choice 用户的选择。
     */
    private void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                calculateTax();
                break;
            case 2:
                adjustTaxRates();
                break;
            case 3:
                adjustThreshold();
                break;
            case 4:
                System.out.println("感谢您使用个人所得税计算器，再见！");
                break;
            default:
                System.out.println("未知选项，请重新选择。");
        }
    }

    /**
     * 计算和显示个人所得税。
     */
    private void calculateTax() {
        UserInputHandler inputHandler = new UserInputHandler();
        double income = inputHandler.getIncome();
        TaxCalculator calculator = new TaxCalculator();
        calculator.displayResult(income);
    }

    public static void main(String[] args) {
        UIController controller = new UIController();
        controller.showMainWindow();
    }
}
