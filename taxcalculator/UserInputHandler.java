package taxcalculator;

import java.util.Scanner;

/**
 * 用户输入处理类，用于从用户获取月工资薪金总额。
 */
public class UserInputHandler {
    private Scanner scanner;

    /**
     * 构造函数，初始化Scanner对象以获取用户输入。
     */
    public UserInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * 获取用户输入的月工资薪金总额。
     * 
     * @return 用户输入的月工资薪金总额，必须是大于等于零的数值。
     */
    public double getIncome() {
        System.out.println("请输入您的月工资薪金总额：");
        while (true) {
            if (scanner.hasNextDouble()) {
                double input = scanner.nextDouble();
                if (input >= 0) {
                    return input;
                } else {
                    System.out.println("输入无效，请输入一个大于等于零的数字：");
                }
            } else {
                System.out.println("输入无效，请输入一个数字：");
                scanner.next(); // 清空输入缓冲区
            }
        }
    }
}
