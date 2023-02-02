package singleton;

import java.util.concurrent.TimeUnit;

/**
 * 懒汉式
 * 达到按需初始化，造成线程不安全
 * 试图用同步代码块，效率下降
 *
 * @author Alfred.Ning
 * @date 2023年02月03日 06:38:00
 */
public class Type5 {

    private static Type5 INSTANCE;

    private Type5() {

    }

    public static Type5 getInstance() {
        if (INSTANCE == null) { // 因为这里可能有两个线程同时进入，竞争锁进行等待
            // 做不到线程安全
            synchronized (Type5.class) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Type5();
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Type5.getInstance().hashCode());
            }).start();
        }
    }
}
