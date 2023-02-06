package chain;

/**
 * 抽象角色
 *
 * @author Alfred.Ning
 * @since 2023年02月06日 20:54:00
 */
public abstract class Handler {
    /**
     * 持有后继的责任对象
     */
    protected Handler successor;

    /**
     * 处理请求的方法
     */
    public abstract String handleFeeRequest(String user, double free);

    public Handler getSuccessor() {
        return successor;
    }

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }
}
