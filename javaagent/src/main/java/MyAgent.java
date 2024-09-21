import com.nq.cost.agent.ServiceAgent;

import java.lang.instrument.Instrumentation;

public class MyAgent {
    public static void premain(String args, Instrumentation instrumentation) {
//        System.out.println("premain !!!");
        instrumentation.addTransformer(new ServiceAgent());
    }
}
