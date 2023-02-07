package composite.transparent;

/**
 * 抽象构件
 *
 * @author Alfred.Ning
 * @since 2023年02月07日 20:18:00
 */
public interface Component {
    public void add(Component c);

    public void remove(Component c);

    public Component getChild(int i);

    public void operation();
}
