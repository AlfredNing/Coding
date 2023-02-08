package bridge;

/**
 * 具体实现
 *
 * @author Alfred.Ning
 * @since 2023年02月08日 22:40:00
 */
public class ConcreteImplementorA implements Implementor{
    @Override
    public void operationImpl() {
        System.out.println("具体实现A");
    }
}
