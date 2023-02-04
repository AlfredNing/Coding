package factory.simple;

/**
 * @author Alfred.Ning
 * @date 2023年02月04日 10:31:00
 */
public class PhoneFactory {
    public Phone makePhone(String phoneType) {
        if ("MiPhone".equals(phoneType)) {
            return new MiPhone();
        }
        if ("ApplePhone".equals(phoneType)) {
            return new ApplePhone();
        }
        return null;
    }
}
