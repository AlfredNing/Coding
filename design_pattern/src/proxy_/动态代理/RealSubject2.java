package proxy_.动态代理;

/**
 * @author Alfred.Ning
 * @since 2023年02月07日 20:54:00
 */
public class RealSubject2 implements ISubject {
    @Override
    public void doAction() {
        System.out.println("Real Action2 Here!");
    }
}