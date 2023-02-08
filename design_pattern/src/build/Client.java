package build;

/**
 * @author Alfred.Ning
 * @since 2023年02月08日 08:08:00
 */
public class Client {
    public static void main(String[] args) {
        Car car = new CarBuilder().comfort("很舒适").power("动力一般").type("紧凑型车").build();

        System.out.println(car);
    }
}
