package singleton;

/**
 * 饿汉式
 * 类加载到内存，就实例化一个单例，JVM保证线程安全
 * 简单使用【推荐使用】
 * 唯一缺点：不管用到与否，类加载时完成实例化
 *
 * @author Alfred.Ning
 * @date 2023年02月03日 06:32:00
 */
public class Type1 {
    private static final Type1 INSTANCE = new Type1();

    private Type1() {

    }

    public static Type1 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        Type1 instance1 = Type1.getInstance();
        Type1 instance2 = Type1.getInstance();
        System.out.println(instance1 == instance2);
    }
}
