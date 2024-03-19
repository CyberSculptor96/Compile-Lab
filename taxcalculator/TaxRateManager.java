package taxcalculator;

/**
 * 税率管理器类，用于管理税率区间和起征点。
 */
public class TaxRateManager {
    private static TaxRateManager instance;
    
    // 定义税率区间和对应的税率，区间上限和税率
    private final double[][] taxBrackets = {
        {0, 3000, 0.03},
        {3000, 12000, 0.1},
        {12000, 25000, 0.2},
        {25000, 35000, 0.25},
        {35000, 55000, 0.3},
        {55000, 80000, 0.35},
        {80000, Double.MAX_VALUE, 0.45} // 使用Double.MAX_VALUE处理超过80000的情况
    };
    
    // 定义起征点
    private double threshold = 5000;
    
    /**
     * 私有构造函数，防止外部直接创建实例。
     */
    private TaxRateManager() {}
    
    /**
     * 获取TaxRateManager的单例实例。
     * 
     * @return TaxRateManager的单例实例。
     */
    public static TaxRateManager getInstance() {
        if (instance == null) {
            instance = new TaxRateManager();
        }
        return instance;
    }
    
    /**
     * 获取当前税率区间。
     * 
     * @return 一个二维数组，包含所有税率区间和对应税率。
     */
    public double[][] getTaxBrackets() {
        return taxBrackets;
    }
    
    /**
     * 更新指定的税率区间。
     * 
     * @param bracketIndex 要更新的税率区间索引。
     * @param lowerBound 更新后的下限。
     * @param upperBound 更新后的上限。
     * @param rate 更新后的税率。
     */
    public void updateTaxBracket(int bracketIndex, double lowerBound, double upperBound, double rate) {
        if (bracketIndex >= 0 && bracketIndex < taxBrackets.length) {
            taxBrackets[bracketIndex][0] = lowerBound;
            taxBrackets[bracketIndex][1] = upperBound;
            taxBrackets[bracketIndex][2] = rate;
        } else {
            System.out.println("无效的税率区间索引。");
        }
    }
    
    /**
     * 获取当前起征点。
     * 
     * @return 当前起征点的值。
     */
    public double getThreshold() {
        return threshold;
    }
    
    /**
     * 更新起征点。
     * 
     * @param threshold 新的起征点值。
     */
    public void updateThreshold(double threshold) {
        this.threshold = threshold;
    }
}
