package mediator;

/**
 * @author Alfred.Ning
 * @date 2023年02月05日 16:11:00
 */
public class ConcreteCollageA extends Collage {

    public ConcreteCollageA(Mediator mediator) {
        super(mediator);
    }

    public void operatoin() {
        getMediator().changed(this);
    }
}
