package factory.simple;

/**
 * @author Alfred.Ning
 * @date 2023年02月04日 10:30:00
 */
public class MiPhone implements Phone{
    public MiPhone() {
        this.make();
    }

    @Override
    public void make() {
        System.out.println("make xiaomi phone");
    }
}
