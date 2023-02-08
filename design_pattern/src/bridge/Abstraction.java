package bridge;

/**
 * 抽象接口
 *
 * @author Alfred.Ning
 * @since 2023年02月08日 22:39:00
 */
public abstract class Abstraction {
    protected Implementor implementor;

    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public void operation(){
        implementor.operationImpl();
    }
}
