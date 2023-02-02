package singleton;

import java.util.concurrent.TimeUnit;

/**
 * 懒汉式 双重检查 线程安全
 *
 * @author Alfred.Ning
 * @date 2023年02月03日 06:38:00
 */
public class Type6 {

    private static volatile Type6 INSTANCE; // 必须加上volatile,防止指令重排

    private Type6() {

    }

    public static Type6 getInstance() {
        if (INSTANCE == null) { // 因为这里可能有两个线程同时进入，竞争锁进行等待
            // 做不到线程安全
            synchronized (Type6.class) {
                if (INSTANCE == null) { // 这里必须得判断，防止进入
                    try {
                        TimeUnit.MICROSECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Type6();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Type6.getInstance().hashCode());
            }).start();
        }
    }
}
