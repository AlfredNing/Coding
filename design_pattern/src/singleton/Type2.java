package singleton;

/**
 * @author Alfred.Ning
 * @date 2023年02月03日 06:37:00
 */
public class Type2 {
    private static final Type2 INSTANCE;

    static {
        INSTANCE = new Type2();
    }

    private Type2() {

    }

    public static Type2 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        Type2 instance1 = Type2.getInstance();
        Type2 instance2 = Type2.getInstance();
        System.out.println(instance1 == instance2);
    }
}
