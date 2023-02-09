package state;

/**
 * @author Alfred.Ning
 * @since 2023年02月09日 22:16:00
 */
public class Client {
    public static void main(String[] args) {
        ConcreteStateB stateB = new ConcreteStateB();
        Context context = new Context();
        context.setState(stateB);
        context.request("test");

    }
}
