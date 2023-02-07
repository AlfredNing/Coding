package proxy_.动态代理;

import java.lang.reflect.Proxy;

/**
 * @author Alfred.Ning
 * @since 2023年02月07日 20:54:00
 */
public class ProxyClient {
    public static void main(String[] args) {
        ISubject subject = (ISubject) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class<?>[]{ISubject.class}, // 或RealSubject.class.getInterfaces()
                new SubjectJdkProxyHandler(new RealSubject())); // RealSubject必须实现Subject接口，否则无法强转后调用业务方法
        subject.doAction();

        // 使用同一个SubjectProxyHandler类，可代理不同的类型
        ISubject subject2 = (ISubject) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class<?>[]{ISubject.class}, new SubjectJdkProxyHandler(new RealSubject2())); // 可使用工厂模式创建代理对象
        subject2.doAction();
    }
}
