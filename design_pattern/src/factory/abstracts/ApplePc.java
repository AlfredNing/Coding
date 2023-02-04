package factory.abstracts;

/**
 * @author Alfred.Ning
 * @date 2023年02月04日 10:43:00
 */
public class ApplePc implements Pc {
    public ApplePc() {
        this.make();
    }

    @Override
    public void make() {
        System.out.println("make apple pc");
    }
}
