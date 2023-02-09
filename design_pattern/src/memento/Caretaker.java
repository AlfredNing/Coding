package memento;

/**
 * 负责人角色
 *
 * @author Alfred.Ning
 * @since 2023年02月09日 21:44:00
 */
public class Caretaker {
    private Memento memento;

    /**
     * 备忘录的取值方法
     */
    public Memento retrieveMemento() {
        return this.memento;
    }

    /**
     * 备忘录的赋值方法
     */
    public void saveMemento(Memento memento) {
        this.memento = memento;
    }
}
