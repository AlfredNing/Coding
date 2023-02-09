package prototype.register;

/**
 * 抽象原型
 *
 * @author Alfred.Ning
 * @since 2023年02月09日 12:37:00
 */
public interface Prototype {
    public Prototype clone();

    public String getName();

    public void setName(String name);
}
