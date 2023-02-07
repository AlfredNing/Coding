package visitor;

/**
 * @author Alfred.Ning
 * @since 2023年02月07日 22:41:00
 */
public class Client {
    public static void main(String[] args) {
        // 创建一个结构对象
        ObjectStructure os = new ObjectStructure();
        // 给结构增加一个节点
        os.add(new Games());
        // 给结构增加一个节点
        os.add(new Photo());
        // 创建一个访问者
        Visitor visitor = new AVisitor();
        os.action(visitor);
    }
}
