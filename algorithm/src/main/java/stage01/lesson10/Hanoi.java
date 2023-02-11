package stage01.lesson10;

import java.util.Stack;

/**
 * @ClassName Hanoi
 * @Description 河内塔问题
 * @Author Alfred.Ning
 * @Date 2022/5/28 15:12
 * @Version 1.0
 **/
public class Hanoi {
    // 把n层圆盘从左转移到右
    public static void hanoi1(int n) {
        leftToRight(n);
    }

    // 把1~N层圆盘 从左 -> 右
    private static void leftToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to right");
            return;
        }
        // 1 - n-1 去中
        leftToMid(n - 1);
        System.out.println("Move " + n + " from left to right");
        // n -1 去右
        midToRight(n - 1);
    }

    // 把1~N层圆盘 从中 -> 右
    private static void midToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to right");
            return;
        }
        // 1 - n-1 去左
        midToLeft(n - 1);
        System.out.println("Move " + n + " from mid to right");
        // n -1 左去右
        leftToRight(n - 1);
    }

    // 把1~N层圆盘 从中 -> 左
    private static void midToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to left");
            return;
        }
        // 1 - n-1 去右
        midToRight(n - 1);
        System.out.println("Move " + n + " from mid to left");
        rightToLeft(n - 1);
    }

    // 把1~N层圆盘 从右 -> 左
    private static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to left");
            return;
        }
        // 1 - n-1 去中
        rightToMid(n - 1);
        System.out.println("Move " + n + " from right to left");
        midToLeft(n - 1);
    }

    // 把1~N层圆盘 从左 -> 中
    private static void leftToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to mid");
            return;
        }
        // 1 - n-1 去右
        leftToRight(n - 1);
        System.out.println("Move " + n + " from left to mid");
        rightToMid(n - 1);
    }

    // 把1~N层圆盘 从右 去 中
    private static void rightToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to mid");
            return;
        }
        // 1 - n-1 去左
        rightToLeft(n - 1);
        System.out.println("Move " + n + " from right to mid");
        leftToMid(n - 1);
    }

    public static void hanoi2(int n) {
        if (n > 0) {
            func(n, "left", "right", "mid");
        }
    }

    private static void func(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("Move 1 from " + from + " to " + to);
        } else {
            func(n - 1, from, other, to);
            System.out.println("Move " + n + " from " + from + " to " + to);
            func(n - 1, other, to, from);
        }
    }


    public static class Record {
        public boolean finish1;
        public int base;
        public String from;
        public String to;
        public String other;

        public Record(boolean f1, int b, String f, String t, String o) {
            finish1 = false;
            base = b;
            from = f;
            to = t;
            other = o;
        }
    }

    // 任何递归的都可以改成非递归的
    public static void hani3(int n) {
        if (n < 1) {
            return;
        }
        Stack<Record> stack = new Stack<>();
        stack.add(new Record(false, n, "left", "right", "mid"));
        while (!stack.isEmpty()) {
            Record cur = stack.pop();
            if (cur.base == 1) {
                System.out.println("Move 1 from " + cur.from + " to " + cur.to);
                if (!stack.isEmpty()) {
                    stack.peek().finish1 = true;
                }
            } else {
                if (!cur.finish1){
                    stack.push(cur);
                    stack.push(new Record(false, cur.base - 1, cur.from, cur.other, cur.to));
                } else {
                    System.out.println("Move "+cur.base+" from " + cur.from + " to " + cur.to);
                    stack.push(new Record(false, cur.base - 1, cur.other, cur.to, cur.from));
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 3;
        hanoi1(n);
        System.out.println("============");
        hanoi2(n);
//		System.out.println("============");
//		hanoi3(n);
    }
}
