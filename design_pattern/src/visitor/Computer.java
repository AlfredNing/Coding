package visitor;

/**
 * 抽象节点
 *
 * @author Alfred.Ning
 * @since 2023年02月07日 22:37:00
 */
public interface Computer {
    void accept(Visitor visitor);
}
