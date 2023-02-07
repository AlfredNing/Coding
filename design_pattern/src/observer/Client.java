package observer;

/**
 * @author Alfred.Ning
 * @since 2023年02月07日 20:03:00
 */
public class Client {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        Observer concreteObserver = new ConcreteObserver();
        subject.attach(concreteObserver);
        subject.change("new State");
    }
}
