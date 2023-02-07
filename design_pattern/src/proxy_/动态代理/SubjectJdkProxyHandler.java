package proxy_.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Alfred.Ning
 * @since 2023年02月07日 20:53:00
 */
public class SubjectJdkProxyHandler implements InvocationHandler {
    private Object realSubject;

    public SubjectJdkProxyHandler(Object realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(">> doWhatever start"); // 扩展进行额外的功能操作(如鉴权、计时、日志等)
        Object result = method.invoke(realSubject, args); // 执行目标对象方法
        System.out.println("doWhatever end <<");   // 扩展进行额外的功能操作(如鉴权、计时、日志等)
        return result;
    }
}
