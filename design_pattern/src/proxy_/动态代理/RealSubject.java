package proxy_.动态代理;

/**
 * 真实对象
 *
 * @author Alfred.Ning
 * @since 2023年02月07日 20:51:00
 */
public class RealSubject implements ISubject {
    @Override
    public void doAction() {
        System.out.println("Real Action Here!");
    }
}
