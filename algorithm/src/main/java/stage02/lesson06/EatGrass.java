package stage02.lesson06;

/**
 * @ClassName EatGrass
 * @Description 牛羊吃草
 * @Author Alfred.Ning
 * @Date 8/27/2022 6:45 PM
 * @Version 1.0
 **/
public class EatGrass {

    public static void main(String[] args) {
        for (int i = 0; i <= 50; i++) {
            System.out.println(i + " : "  +winner1(i) );

        }
    }


    // 暴力方法
    public static String winner1(int n) {
        if (n < 5) {
            return (n == 0 || n == 2) ? "后手" : "先手";
        }
        int base = 1;
        while (base <= n) {
            if (winner1(n - base).equals("后手")) {
                return "先手";
            }
            if (base > n / 4) { // 防止base*4之后溢出
                break;
            }
            base *= 4;
        }
        return "后手";
    }

}
