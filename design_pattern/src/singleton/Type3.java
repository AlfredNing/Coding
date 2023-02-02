package singleton;

import java.util.concurrent.TimeUnit;

/**
 * 懒汉式【线程不安全】
 *
 * @author Alfred.Ning
 * @date 2023年02月03日 06:38:00
 */
public class Type3 {

    private static Type3 INSTANCE;

    private Type3() {

    }

    public static Type3 getInstance()  {
        if (INSTANCE == null) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Type3();
        }
        return INSTANCE;
    }

    // 线程不安全模拟
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Type3.getInstance().hashCode());
            }).start();
        }
    }
}
