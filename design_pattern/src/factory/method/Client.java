package factory.method;

/**
 * @author Alfred.Ning
 * @date 2023年02月04日 10:37:00
 */
public class Client {
    public static void main(String[] args) {
        XiaoMiFactory xiaoMiFactory = new XiaoMiFactory();
        AppleFactory appleFactory = new AppleFactory();
        xiaoMiFactory.makePhone();
        appleFactory.makePhone();
    }
}
