package taxcalculator;

/**
 * 个人所得税计算器类，用于根据给定的收入计算应缴纳的个人所得税。
 */
public class TaxCalculator {
    private TaxRateManager taxRateManager;

    /**
     * 构造函数，初始化TaxRateManager实例。
     */
    public TaxCalculator() {
        this.taxRateManager = TaxRateManager.getInstance();
    }

    /**
     * 根据给定的薪资计算应缴纳的个人所得税。
     * 
     * @param salary 薪资。
     * @return 应缴纳的个人所得税。
     */
    public double calculateTax(double salary) {
        double threshold = taxRateManager.getThreshold();
        double taxableIncome = salary - threshold;
        if (taxableIncome <= 0) {
            return 0;
        }

        double tax = 0;
        double[][] taxBrackets = taxRateManager.getTaxBrackets();
        for (double[] bracket : taxBrackets) {
            // 只有当收入超过当前税率区间的下限时，才计算该区间的税
            if (taxableIncome > bracket[0]) {
                // 确定税率区间的上限，如果是最后一个区间，上限为税前收入
                double upperBound = bracket[1] == Double.MAX_VALUE ? taxableIncome : bracket[1];
                // 计算在当前税率区间内的应税所得额
                double taxForBracket = Math.min(taxableIncome, upperBound) - bracket[0];
                // 根据区间税率计算税额，并累加到总税额中
                tax += taxForBracket * bracket[2];
                // 如果收入没有超过当前区间的上限，停止遍历后续税率区间
                if (taxableIncome <= upperBound) {
                    break;
                }
            }
        }
        return tax;
    }
    
    /**
     * 显示给定收入的个人所得税计算结果。
     * 
     * @param income 收入。
     */
    public void displayResult(double income) {
        double tax = calculateTax(income);
        System.out.printf("您需要缴纳的个人所得税为：%.2f元\n", tax);
    }
}
