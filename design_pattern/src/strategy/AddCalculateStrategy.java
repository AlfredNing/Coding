package strategy;

/**
 * 具体策略
 *
 * @author Alfred.Ning
 * @date 2023年02月03日 20:40:00
 */
public class AddCalculateStrategy implements CalculateStrategy {

    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
