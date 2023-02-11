package stage01.lesson10;

/**
 * @ClassName NQueue
 * @Description N皇后问题
 * @Author Alfred.Ning
 * @Date 2022/6/4 12:05
 * @Version 1.0
 **/
public class NQueue {
    public static int number1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process1(0, record, n);
    }

    // 当前来到i行，record [index] : index代表行，record[index] 代表之前第i行的皇后，放在了y列上
    // i之前的行不关心，每一次都要尝试i之后的行
    private static int process1(int i, int[] record, int n) {
        if (i == n) { // 放满，结束
            return 1;
        }
        // 第i行皇后，放在那一列，需要尝试
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) { // 检验通过 放入
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    private static boolean isValid(int[] record, int i, int j) {
        // 同行已经不需要判断
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(j - record[k]) == Math.abs(i - k)) { // 同列 同对角判断
                return false;
            }
        }
        return true;
    }

    // 位运算进行加速  请不要超过32皇后问题
    public static int number2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        // 32位数字是-1 如果是13皇后问题，limit 最右13个1，其他都是0
        int limit = n == 32 ? -1 : (1 << n) - 1;

        return process2(limit, 0, 0, 0);
    }

    /**
     * 给定问题规模，给定限制，返回多少种摆法
     *
     * @param limit       划定问题规模
     * @param colLim      列的限制
     * @param leftDiaLim  左斜线的限制
     * @param rightDiaLim 右斜线的限制
     * @return
     */
    private static int  process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) { // 列限制 = limit
            return 1; // 皇后满了
        }
        // 所有皇后的候选位置都在pos上面
        // colLim | leftDiaLim | rightDiaLim 所有的限制
        //  ~(colLim | leftDiaLim | rightDiaLim) 左侧的0成为干扰因素，右侧的1是需要尝试拜访皇后的位置
        // limit *  limit &(~(colLim | leftDiaLim | rightDiaLim)) 过滤掉左边的0,也可以防止右边元素过界
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));

        // 提取最右侧的1 x & (~x + 1)
        int mostRightOnce = 0;
        int res = 0;
        while (pos != 0){
            mostRightOnce = pos & (~pos + 1);
            pos = pos - mostRightOnce; // 减掉最右侧的1
            res += process2(
                    limit,
                    colLim | mostRightOnce,
                    (leftDiaLim  | mostRightOnce) << 1,
                    (rightDiaLim | mostRightOnce) >>> 1
            );
        }
        return res;
    }


    public static void main(String[] args) {
        int n = 15;

        long start = System.currentTimeMillis();
        System.out.println(number2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(number1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }
}
