package flyweight.complex;

import java.util.HashMap;
import java.util.Map;

/**
 * 复合享元角色
 *
 * @author Alfred.Ning
 * @since 2023年02月07日 20:44:00
 */
public class ConcreteCompositeFlyweight implements Flyweight {

    private Map<Character, Flyweight> files = new HashMap<Character, Flyweight>();

    /**
     * 增加一个新的单纯享元对象到聚集中
     */
    public void add(Character key, Flyweight fly) {
        files.put(key, fly);
    }

    /**
     * 外蕴状态作为参数传入到方法中
     */
    @Override
    public void operation(String state) {
        Flyweight fly = null;
        for (Object o : files.keySet()) {
            fly = files.get(o);
            fly.operation(state);
        }
    }

}
