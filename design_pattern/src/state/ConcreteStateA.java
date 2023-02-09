package state;

/**
 * @author Alfred.Ning
 * @since 2023年02月09日 22:16:00
 */
public class ConcreteStateA implements State{
    @Override
    public void handle(String sampleParameter) {
        System.out.println("具体状态A..." + sampleParameter);
    }
}
