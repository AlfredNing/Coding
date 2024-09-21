import com.nq.cost.agent.UserServiceImpl;

public class UserServiceImplTest {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.findUser("nq");
    }
}
