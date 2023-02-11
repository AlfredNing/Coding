package prod.hw;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * @ClassName NJ8合并表记录
 * @Author 宁强-34436
 * @Date 2022/5/27 15:50
 * @Email qiang.ning@going-link.com
 * @Descirption
 **/
public class NJ8合并表记录 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        int idx = 0;
        while (sc.hasNext()) {
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                map.put(a, map.getOrDefault(a, 0) + b);
            }
            break;
        }
        map.forEach((x, y) -> {
            System.out.println(x + " " + y);
        });
    }
}
