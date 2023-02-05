package mediator;

/**
 * @author Alfred.Ning
 * @date 2023年02月05日 16:09:00
 */
public class Collage {
    private Mediator mediator;

    public Collage(Mediator mediator) {
        this.mediator = mediator;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
}
