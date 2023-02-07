package visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 结构对象角色
 *
 * @author Alfred.Ning
 * @since 2023年02月07日 22:41:00
 */
public class ObjectStructure {
    private List<Computer> computers = new ArrayList<Computer>();

    public void action(Visitor visitor) {
        computers.forEach(c -> {
            c.accept(visitor);
        });
    }

    public void add(Computer computer) {
        computers.add(computer);
    }
}
