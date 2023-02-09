package state;

/**
 * 环境类
 *
 * @author Alfred.Ning
 * @since 2023年02月09日 22:14:00
 */
public class Context {
    private State state;

    public void setState(State state) {
        this.state = state;
    }

    /**
     * 用户感兴趣的接口方法
     */
    public void request(String sampleParameter) {
        //转调state来处理
        state.handle(sampleParameter);
    }
}
