package visitor;

/**
 * 具体节点
 *
 * @author Alfred.Ning
 * @since 2023年02月07日 22:38:00
 */
public class Games implements Computer {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void play() {
        System.out.println("play lol");
    }
}
