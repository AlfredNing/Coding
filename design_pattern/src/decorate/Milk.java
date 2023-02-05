package decorate;

/**
 * @author Alfred.Ning
 * @date 2023年02月06日 07:49:00
 */
public class Milk extends CondimentDecorator {
    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return 1 + beverage.cost();
    }
}
