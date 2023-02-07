package observer;

/**
 * @author Alfred.Ning
 * @since 2023年02月07日 19:59:00
 */
public class ConcreteSubject extends Subject {
    private String state;

    public String getState() {
        return state;
    }

    public void change(String newState) {
        state = newState;
        System.out.println("主題状态为：" + state);
        this.notifyObservers(state);
    }
}
