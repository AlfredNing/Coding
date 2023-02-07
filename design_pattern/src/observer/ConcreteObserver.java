package observer;

/**
 * @author Alfred.Ning
 * @since 2023年02月07日 20:02:00
 */
public class ConcreteObserver implements Observer {
    private String observerState;

    @Override
    public void update(String state) {
        /**
        * 更新观察者的状态，使其与目标的状态保持一致
        */
        observerState = state;
        System.out.println("状态为：" + observerState);
    }
}
