package stage02.lesson05;

/**
 * @ClassName SegmentTree
 * @Description 线段树
 * @Author Alfred.Ning
 * @Date 2022/8/7 15:47
 * @Version 1.0
 **/
public class SegmentTreeCode {


    public static void main(String[] args) {
        int[] origin = { 2, 1, 1, 2, 3, 4, 5 };
        SegmentTree seg = new SegmentTree(origin);
        int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
        int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
        int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
        int L = 2; // 操作区间的开始位置 -> 可变
        int R = 5; // 操作区间的结束位置 -> 可变
        int C = 4; // 要加的数字或者要更新的数字 -> 可变
        // 区间生成，必须在[S,N]整个范围上build
        seg.build(S, N, root);
        // 区间修改，可以改变L、R和C的值，其他值不可改变
        seg.add(L, R, C, S, N, root);
        // 区间更新，可以改变L、R和C的值，其他值不可改变
        seg.update(L, R, C, S, N, root);
        // 区间查询，可以改变L和R的值，其他值不可改变
        long sum = seg.query(L, R, S, N, root);
        System.out.println(sum);

        System.out.println("对数器测试开始...");
        System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

    }

    // 暴力方法
    public static class Right {
        public int[] arr;

        public Right(int[] origin) {
            // 下表从1开始计算
            arr = new int[origin.length + 1];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public void update(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] = C;
            }
        }

        public long query(int L, int R) {
            long ans = 0;
            for (int i = L; i <= R; i++) {
                ans += arr[i];
            }
            return ans;
        }


    }

    public static int[] genarateRandomArray(int len, int max) {
        int size = (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    public static class SegmentTree {
        private int maxN; // 数组长度
        private int[] arr;
        private int[] sum; // 求和结果的数组
        private int[] lazy; // 负责懒加载
        private int[] change; // 更新的值
        private boolean[] update; // 更新懒惰标记

        // 构建数组
        public SegmentTree(int[] origin) {
            maxN = origin.length + 1;
            arr = new int[maxN];
            // 下标也是从1开始
            for (int i = 1; i < maxN; i++) {
                arr[i] = origin[i - 1];
            }
            // 设置其他保存结果的数组为4n,肯定能装下所有补充的数组
            sum = new int[maxN << 2];
            lazy = new int[maxN << 2];
            change = new int[maxN << 2];
            update = new boolean[maxN << 2];
        }

        // 构建上层节点sum值
        public void pushUp(int rt) {
            // 上面得数组依赖于下面得数组
            // i = 2i + 2i + 1; 位运算比较快
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        // ln 左子树节点个数， rn右子树节点个数
        // 懒增加 懒更新 分发给左右两个子范围
        public void pushDown(int rt, int ln, int rn) {
            // 懒更新
            if (update[rt]) {
                // 左右子树
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];

                lazy[rt << 1] = 0;
                lazy[rt << 1 | 1] = 0;

                sum[rt << 1] = change[rt] * ln;
                sum[rt << 1 | 1] = change[rt] * rn;

                update[rt] = false;

            }

            // 懒加载
            if (lazy[rt] != 0) { // 说明之前右懒加载得
                // 更新左子树
                lazy[rt << 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * ln;
                // 更新右子树
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1 | 1] += lazy[rt] * rn;

                // 重置lazy
                lazy[rt] = 0;

            }

        }


        // 初始化，将sum数组填充好
        // 在arr[l...r]范围上去构建1-n
        // rt 这个范围在sum中得下标
        public void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }

            // 求出中间值
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1); // 2*i
            build(mid + 1, r, rt << 1 | 1); // 2*i + 1
            pushUp(rt);
        }

        // 添加方法

        /*
         * L ... R范围上添加C
         * @param L 实际任务左范围
         * @param R 实际任务右范围
         * @param C 要添加的值
         * @param l 当前任务左范围
         * @param r 当前任务右范围
         * @param rt 当前任务的节点
         */
        public void add(int L, int R, int C, int l, int r, int rt) {
            // 当前任务包含实际任务
            if (L <= l && R >= r) {
                sum[rt] += C * (r - l + 1) ;
                lazy[rt] += C;
                return;
            }
            // 任务没有包含全部
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);

            if (L <= mid){
                add(L, R, C, l, mid, rt << 1);
            }
            if (R > mid){
                add(L, R, C, mid + 1,r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        // L...R 范围上全部值改为C
        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                update[rt]  = true;
                change[rt] = C;
                sum[rt] = C * (r - l +1);
                lazy[rt] = 0;
                return;
            }
            int mid = (r  + l) >> 1;
            pushDown(rt, mid - l + 1, r - mid);

            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid){
                update(L, R, C, mid + 1,r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        // 获取求和结果[L..R]
        public long query(int L, int R, int l, int r, int rt){
            if (L <= l && r <= R) {
                return sum[rt];
            }
            int mid = (r  + l) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            long ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }
    }


    public static boolean test() {
        int len = 100;
        int max = 1000;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = genarateRandomArray(len, max);
            SegmentTree seg = new SegmentTree(origin);
            int S = 1;
            int N = origin.length;
            int root = 1;
            seg.build(S, N, root);
            Right rig = new Right(origin);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                int C = (int) (Math.random() * max) - (int) (Math.random() * max);
                if (Math.random() < 0.5) {
                    seg.add(L, R, C, S, N, root);
                    rig.add(L, R, C);
                } else {
                    seg.update(L, R, C, S, N, root);
                    rig.update(L, R, C);
                }
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                long ans1 = seg.query(L, R, S, N, root);
                long ans2 = rig.query(L, R);
                if (ans1 != ans2) {
                    return false;
                }
            }
        }
        return true;
    }

}
