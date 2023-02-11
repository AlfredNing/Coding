package prod.hw;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * @ClassName HJ70矩阵乘法计算量估算
 * @Author 宁强-34436
 * @Date 2022/5/26 16:24
 * @Email qiang.ning@going-link.com
 * @Descirption
 **/
public class HJ70矩阵乘法计算量估算 {

    /*
    A是一个50×10的矩阵，B是10×20的矩阵，C是20×5的矩阵
    计算不同的计算顺序需要进行的乘法次数。
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            // 总共2n个数
            int n = sc.nextInt();
            int[][] input = new int[n][2];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 2; j++) {
                    input[i][j] = sc.nextInt();
                }
            }
            // 输入规则
            String rule = sc.next();
            System.out.println(estimate(input, rule));
        }
    }

    private static int estimate(int[][] input, String rule) {
        int res = 0;
        Stack<int[]> stack1 = new Stack<>(); // 存放矩阵行
        Stack<Character> stack2 = new Stack<>(); // 存放矩阵号
        for (int i = 0; i < rule.length(); i++) {
            char c = rule.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                stack1.push(input[c - 'A']);
            } else if (c == '(') {
                // 遇到左括号
                stack2.push(c);
            } else if (c == ')' && !stack2.isEmpty()) {
                stack2.pop();
                int[] n2 = stack1.pop();
                int[] n1 = stack1.pop();
                res += n1[0] * n1[1] * n2[1];
                // 压回stack1
                int[] tmp = {n1[0], n2[1]};
                stack1.push(tmp);
            }
        }
        return res;
    }
}

