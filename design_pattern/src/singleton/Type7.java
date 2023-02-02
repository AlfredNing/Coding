package singleton;

/**
 * 静态内部类方式
 * JVM保证单例
 * 加载外部类时候不会加载静态内部类，按需加载
 *
 * @author Alfred.Ning
 * @date 2023年02月03日 07:20:00
 */
public class Type7 {
    private Type7() {
    }

    private static class Typ7Holder {
        public static final Type7 INSTANCE = new Type7();
    }

    public static Type7 getInstance() {
        return Typ7Holder.INSTANCE;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Type7.getInstance().hashCode());
            }).start();
        }
    }
}
