package factory.simple;

/**
 * @author Alfred.Ning
 * @date 2023年02月04日 10:33:00
 */
public class Client {
    public static void main(String[] args) {
        PhoneFactory phoneFactory = new PhoneFactory();
        Phone miPhone = phoneFactory.makePhone("MiPhone");
        Phone iPhone = phoneFactory.makePhone("ApplePhone");
    }
}
