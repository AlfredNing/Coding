package adapter.类的适配器模式;

/**
 * 适配器角色
 *
 * @author Alfred.Ning
 * @since 2023年02月08日 08:18:00
 */
public class Adapter extends Adaptee implements Target{
    @Override
    public void sampleOperation2() {
        // 适配器方法
    }
}
