package bridge;

/**
 * @author Alfred.Ning
 * @since 2023年02月08日 22:43:00
 */
public class Client {
    public static void main(String[] args) {
        Implementor implementor = new ConcreteImplementorA();
        RefinedAbstraction abstraction = new RefinedAbstraction(implementor);
        abstraction.operation();
        abstraction.otherOperation();
    }
}
