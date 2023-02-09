package state;

/**
 * 抽象状态
 *
 * @author Alfred.Ning
 * @since 2023年02月09日 22:15:00
 */
public interface State {
    /**
     * 对于状态处理
     */
    public void handle(String sampleParameter);
}
