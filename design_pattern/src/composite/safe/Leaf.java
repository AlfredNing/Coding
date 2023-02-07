package composite.safe;

import composite.transparent.Component;

/**
 * 树叶构件
 *
 * @author Alfred.Ning
 * @since 2023年02月07日 20:18:00
 */
public class Leaf implements composite.safe.Component {
    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    public void operation() {
        System.out.println("树叶" + name + "：被访问！");
    }
}
