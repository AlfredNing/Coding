package factory.abstracts;

/**
 * @author Alfred.Ning
 * @date 2023年02月04日 10:45:00
 */
public class Client {
    public static void main(String[] args) {
        AbstractFactory xiaoMiFactory = new XiaoMiFactory();
        AbstractFactory appleFactory = new AppleFactory();
        xiaoMiFactory.makePhone();
        appleFactory.makePhone();
        xiaoMiFactory.makePc();
        appleFactory.makePc();
    }
}
