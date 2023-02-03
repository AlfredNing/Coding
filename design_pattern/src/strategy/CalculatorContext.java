package strategy;

/**
 * 环境类
 *
 * @author Alfred.Ning
 * @date 2023年02月03日 20:41:00
 */
public class CalculatorContext {
    private CalculateStrategy strategy;

    public CalculatorContext(CalculateStrategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}
