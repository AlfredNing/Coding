package derocator;

/**
 * @author Alfred.Ning
 * @date 2023年02月06日 07:50:00
 */
public class Client {
    public static void main(String[] args) {
        Beverage beverage = new HouseBland();
        beverage = new Mocha(beverage);
        beverage = new Milk(beverage);
        System.out.println(beverage.cost());
    }
}
