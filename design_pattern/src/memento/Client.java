package memento;

/**
 * @author Alfred.Ning
 * @since 2023年02月09日 21:44:00
 */
public class Client {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();
        originator.setState("on");
        caretaker.saveMemento(originator.createMemento());
        originator.setState("off");
        originator.restoreMemento(caretaker.retrieveMemento());
        System.out.println(originator.getState());
    }
}
