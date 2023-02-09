package template;

/**
 * @author Alfred.Ning
 * @since 2023年02月09日 21:57:00
 */
public class ConcreteTemplate extends AbstractTemplate {
    //基本方法的实现
    @Override
    public void abstractMethod() {
        //业务相关的代码
    }

    //重写父类的方法
    @Override
    public void hookMethod() {
        //业务相关的代码
    }
}
