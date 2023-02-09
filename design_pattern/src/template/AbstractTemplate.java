package template;

/**
 * 抽象模板角色
 *
 * @author Alfred.Ning
 * @since 2023年02月09日 21:53:00
 */
public abstract class AbstractTemplate {
    /**
     * 模板方法
     */
    public void templateMethod() {
        //调用基本方法
        abstractMethod();
        hookMethod();
        concreteMethod();
    }

    /**
     * 基本方法的声明（由子类实现）
     */
    protected abstract void abstractMethod();

    /**
     * 基本方法(空方法)
     */
    protected void hookMethod() {
    }

    /**
     * 基本方法（已经实现）
     */
    private final void concreteMethod() {
        //业务相关的代码
    }
}
