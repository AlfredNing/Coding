package mediator;

/**
 * @author Alfred.Ning
 * @date 2023年02月05日 16:10:00
 */
public class ConcreteMediator implements Mediator{
    private ConcreteCollageA collageA;
    private ConcreteCollageB collageB;

    public void setCollageB(ConcreteCollageB collageB) {
        this.collageB = collageB;
    }

    public void setCollageA(ConcreteCollageA collageA) {
        this.collageA = collageA;
    }

    @Override
    public void changed(Collage collage) {
        /**
         * 具体实现
         */
    }
}
