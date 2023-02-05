package facade;

/**
 * @author Alfred.Ning
 * @date 2023年02月05日 15:57:00
 */
public class Facade {
    public void test() {
        ModelA modelA = new ModelA();
        modelA.testA();

        ModelB modelB = new ModelB();
        modelB.testB();

        ModelC modelC = new ModelC();
        modelC.testC();

    }
}
