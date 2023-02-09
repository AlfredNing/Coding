package command;

/**
 * 请求者角色类
 *
 * @author Alfred.Ning
 * @since 2023年02月09日 08:27:00
 */
public class Invoker {
    /**
     * 持有命令对象
     */
    private Command command = null;

    /**
     * 构造方法
     */
    public Invoker(Command command) {
        this.command = command;
    }

    /**
     * 行动方法
     */
    public void action() {
        command.execute();
    }
}
