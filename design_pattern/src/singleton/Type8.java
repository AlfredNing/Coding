package singleton;

/**
 * 枚举
 * 解决线程同步，防止反序列化
 *
 * @author Alfred.Ning
 * @date 2023年02月03日 07:26:00
 */
public enum Type8 {
    INSTANCE;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Type8.INSTANCE.hashCode());
            }).start();
        }
    }
}
