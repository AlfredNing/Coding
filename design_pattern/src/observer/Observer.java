package observer;

/**
 * 抽象观察者角色
 *
 * @author Alfred.Ning
 * @since 2023年02月07日 19:56:00
 */
public interface Observer {
    /**
     * 更新接口
     * @param state 状态
     */
    void update(String state);
}
