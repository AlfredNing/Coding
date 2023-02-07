package composite.transparent;

import java.util.ArrayList;

/**
 * 树枝构件
 *
 * @author Alfred.Ning
 * @since 2023年02月07日 20:19:00
 */
public class Composite implements Component {
    private ArrayList<Component> children = new ArrayList<Component>();

    public void add(Component c) {
        children.add(c);
    }

    public void remove(Component c) {
        children.remove(c);
    }

    public Component getChild(int i) {
        return children.get(i);
    }

    public void operation() {
        for (Component obj : children) {
            obj.operation();
        }
    }

}
