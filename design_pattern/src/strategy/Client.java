package strategy;

/**
 * @author Alfred.Ning
 * @date 2023年02月03日 20:40:00
 */
public class Client {
    public static void main(String[] args) {
        int a = 4, b = 2;
        CalculatorContext context = new CalculatorContext(new AddCalculateStrategy());
        System.out.println("a + b = " + context.executeStrategy(a, b));

        CalculatorContext context1 = new CalculatorContext(new MinusCalculateStrategy());
        System.out.println("a + b = " + context1.executeStrategy(a, b));


    }
}
