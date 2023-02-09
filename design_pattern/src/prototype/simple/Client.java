package prototype.simple;

/**
 * @author Alfred.Ning
 * @since 2023年02月09日 12:35:00
 */
public class Client {
    private Prototype prototype;

    public Client(Prototype prototype) {
        this.prototype = prototype;
    }

    public void doOperation(Prototype example) {
        Prototype copyPrototype = (Prototype) example.clone();
    }
}
