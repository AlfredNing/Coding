package factory.method;

import factory.simple.MiPhone;
import factory.simple.Phone;

/**
 * @author Alfred.Ning
 * @date 2023年02月04日 10:36:00
 */
public class XiaoMiFactory implements AbstractFactory {

    @Override
    public Phone makePhone() {
        return new MiPhone();
    }
}
