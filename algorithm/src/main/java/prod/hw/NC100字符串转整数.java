package prod.hw;

/**
 * @ClassName NC100字符串转整数
 * @Author 宁强-34436
 * @Date 2022/5/26 15:00
 * @Email qiang.ning@going-link.com
 * @Descirption
 **/
public class NC100字符串转整数 {
    public int StrToInt(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        int res = 0;
        int index = 0;
        int n = s.length();
        // 去掉前导空格
        while (index < n) {
            if (s.charAt(index) == ' ') {
                index++;
            } else {
                break;
            }
        }
        if (index == n) {
            return 0;
        }
        // 处理第一个符号是正负号
        int sign = 1;
        if (s.charAt(index) == '+') {
            index++;
        } else if (s.charAt(index) == '-') {
            index++;
            sign = -1;
        }
        // 处理完符号
        if (index == n) {
            return 0;
        }

        while (index < n) {
            char c = s.charAt(index);
            if (c < '0' || c > '9') {
                // 不合法
                break;
            }
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (c - '0') > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (c - '0') > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }
            res = res * 10 + sign * (c - '0');
            index++;
        }
        return res;
    }
}
