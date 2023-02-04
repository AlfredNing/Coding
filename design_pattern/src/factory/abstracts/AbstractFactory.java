package factory.abstracts;

import factory.simple.Phone;

/**
 * @author Alfred.Ning
 * @date 2023年02月04日 10:44:00
 */
public interface AbstractFactory {
    Phone makePhone();
    Pc makePc();
}
