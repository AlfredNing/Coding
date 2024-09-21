import java.lang.instrument.Instrumentation;

public class MyAttachAgent {
    public static void agentmain(String args, Instrumentation instrumentation) {
        System.out.println("premain !!!");
    }
}
