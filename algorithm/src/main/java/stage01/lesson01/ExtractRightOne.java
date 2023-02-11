package stage01.lesson01;

/**
 * @ClassName ExtractRightZero
 * @Description 把一个int的数，提取出最右侧的1
 * @Author Alfred.Ning
 * @Date 2022/4/3 10:40
 * @Version 1.0
 **/
public class ExtractRightOne {
    public static int extractRightOne(int n) {
        // 5
        //N： 0101
        // ~N： 1010
        // ~N + 1：1011
        // N & (~N + 1）：0001
        return n & ((~n) + 1);
    }

    public static void main(String[] args) {

    }
}
