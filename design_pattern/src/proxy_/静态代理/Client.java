package proxy_.静态代理;

/**
 * @author Alfred.Ning
 * @since 2023年02月07日 20:52:00
 */
public class Client {
    public static void main(String[] args) {
        SubjectProxy subject = new SubjectProxy();
        subject.doAction();
        subject.byebye();
    }
}
