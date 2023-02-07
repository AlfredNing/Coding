package visitor;

/**
 * 抽象访问者
 *
 * @author Alfred.Ning
 * @since 2023年02月07日 22:34:00
 */
public interface Visitor {
    void visit(Games games);
    void visit(Photo photo);
}
