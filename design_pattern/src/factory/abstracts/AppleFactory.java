package factory.abstracts;

import factory.simple.ApplePhone;
import factory.simple.MiPhone;
import factory.simple.Phone;

/**
 * @author Alfred.Ning
 * @date 2023年02月04日 10:36:00
 */
public class AppleFactory implements AbstractFactory {

    @Override
    public Phone makePhone() {
        return new ApplePhone();
    }

    @Override
    public Pc makePc() {
        return new ApplePc();
    }
}
