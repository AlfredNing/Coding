package bridge;

/**
 * 扩展抽象类
 *
 * @author Alfred.Ning
 * @since 2023年02月08日 22:41:00
 */
public class RefinedAbstraction extends Abstraction{
    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    public void otherOperation() {
        // 实现一定的功能，可能会使用具体实现部分的实现方法,
        // 但是本方法更大的可能是使用 Abstraction 中定义的方法，
        // 通过组合使用 Abstraction 中定义的方法来完成更多的功能。
        System.out.println("nb");
    }
}
