package stage02.lesson06;

/**
 * @ClassName AppleMinBag
 * @Description 打表规律-苹果使用的最小袋子树
 * @Author Alfred.Ning
 * @Date 8/27/2022 5:45 PM
 * @Version 1.0
 **/
public class AppleMinBag {

    // 暴力方法
    public static int minBags(int apple){
        if (apple < 0){
            return 0;
        }
        int bag6 = -1;
        int bag8 = apple / 8;
        int rest = apple - 8 * bag8;
        while (bag8 >= 0 && rest < 24) {  // >= 24 不能成功
            int restUse6 = miBags6(rest);
            if (restUse6 != -1) {
                bag6 = restUse6;
                break;
            }
            rest = apple - 8 * (--bag8);
        }
        return bag6 == -1 ? -1 : bag6 + bag8;
    }

    private static int miBags6(int rest) {
        return rest % 6 == 0 ? (rest / 6) : -1;
    }

    // 打表
    public static int minBagAwesome(int apple) {
        if ((apple & 1 ) != 0){ // 奇数返回-1
            return -1;
        }
        if (apple < 18) {
            return apple == 0 ? 0 : (apple == 6 || apple == 8)  ? 1
                    : (apple == 12 || apple == 16 || apple == 14) ? 2 : -1;
        }
        return (apple - 18) / 8 + 3;
    }

    public static void main(String[] args) {
        for (int apple = 1; apple <= 100; apple++) {
            System.out.println(apple +" : " + minBags(apple));
        }
    }
}
