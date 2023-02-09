package memento;

/**
 * 备忘录角色类
 *备忘录对象将发起人对象传入的状态存储起来
 * @author Alfred.Ning
 * @since 2023年02月09日 21:42:00
 */
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
