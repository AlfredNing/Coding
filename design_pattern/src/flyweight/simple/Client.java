package flyweight.simple;

/**
 * @author Alfred.Ning
 * @since 2023年02月07日 20:42:00
 */
public class Client {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight fly = factory.factory('a');
        fly.operation("First Call");

        fly = factory.factory('b');
        fly.operation("Second Call");

        fly = factory.factory('b');
        fly.operation("Third Call");
    }
}
