package stage01.lesson07;

/**
 * @ClassName PaperFolding
 * @Description 折痕题目
 * @Author Alfred.Ning
 * @Date 2022/5/2 11:53
 * @Version 1.0
 **/
public class PaperFolding {
    public static void printAllFolds(int N) {
        process(1, N, true);
        System.out.println();
    }

    // level:层数，N:折几次，down:是否下折痕
    public static void process(int level, int N, boolean down) {
        if (level > N) {
            return;
        }
        process(level + 1, N, true);
        System.out.println(down ? "凹" :"凸");
        process(level + 1, N,false);
    }

    public static void main(String[] args) {
        int n = 4;
        printAllFolds(n);
    }
}
