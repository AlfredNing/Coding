package proxy_.静态代理;

/**
 * 代理对象
 *
 * @author Alfred.Ning
 * @since 2023年02月07日 20:51:00
 */
public class SubjectProxy implements ISubject {
    private ISubject subject;

    public SubjectProxy() {
        // RealSubject实例可根据环境变量、配置等创建不同类型的实例(多态)
        subject = new RealSubject(); // 此处仅简单地new实例
    }

    @Override
    public void doAction() {
        System.out.println(">> doWhatever start"); // 扩展进行额外的功能操作(如鉴权、计时、日志等)
        subject.doAction();
        System.out.println("doWhatever end <<");   // 扩展进行额外的功能操作(如鉴权、计时、日志等)
    }

    @Override
    public void byebye() {
        System.out.println("Say goodbye"); // 改变委托类行为(例如实现数据库连接池时避免close关闭连接)
    }
}
