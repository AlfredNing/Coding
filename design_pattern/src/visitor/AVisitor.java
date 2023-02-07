package visitor;

/**
 * 具体访问者
 *
 * @author Alfred.Ning
 * @since 2023年02月07日 22:39:00
 */
public class AVisitor implements Visitor{
    @Override
    public void visit(Games games) {
        games.play();
    }

    @Override
    public void visit(Photo photo) {
        photo.watch();
    }
}
