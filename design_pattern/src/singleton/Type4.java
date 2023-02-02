package singleton;

import java.util.concurrent.TimeUnit;

/**
 * 懒汉式
 * 线程安全，锁类对象的class，效率下降
 *
 * @author Alfred.Ning
 * @date 2023年02月03日 06:38:00
 */
public class Type4 {

    private static Type4 INSTANCE;

    private Type4() {

    }

    public static synchronized Type4 getInstance()  {
        if (INSTANCE == null) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Type4();
        }
        return INSTANCE;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Type4.getInstance().hashCode());
            }).start();
        }
    }
}
