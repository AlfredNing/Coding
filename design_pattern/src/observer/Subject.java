package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象主题角色
 *
 * @author Alfred.Ning
 * @since 2023年02月07日 19:55:00
 */
public abstract class Subject {
    private List<Observer> list = new ArrayList<>();

    public void attach(Observer observer) {
        list.add(observer);
        System.out.println("Attached an Observer");
    }

    public void detach(Observer observer) {
        list.remove(observer);
    }

    public void notifyObservers(String newState) {
        for (Observer observer : list) {
            observer.update(newState);
        }
    }
}
