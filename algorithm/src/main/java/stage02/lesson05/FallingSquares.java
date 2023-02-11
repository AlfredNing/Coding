package stage02.lesson05;

import java.util.*;

/**
 * @ClassName FallingSquares
 * @Description 俄罗斯方块
 * @Author Alfred.Ning
 * @Date 8/21/2022 8:17 PM
 * @Version 1.0
 **/
public class FallingSquares {
    public static class SegmentTree{
        private int[] max; // 每下落之后的最大值
        private int[] change;
        private boolean[] update; // 是否更新
        public SegmentTree(int size){
            int N = size + 1;
            max = new int[N << 2]; // 4n
            change = new int[N << 2];
            update = new boolean[N << 2];
        }

        public void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        public void pushDown(int rt, int ln, int rn){
            if (update[rt]){
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                max[rt << 1] = change[rt];
                max[rt << 1 | 1] = change[rt];
                update[rt] = false;
            }
        }

        public void update(int L,int R, int C, int l, int r,int rt){
            if (L <= l && r <= R ){
                update[rt] = true;
                change[rt] = C ;
                max[rt] = C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt,mid - l + 1, r - mid);
            if (L <= mid) {
                update(L,R,C,l,mid,rt << 1);
            }
            if (R > mid) {
                update(L,R,C,mid + 1,r,rt << 1 | 1);
            }
            pushUp(rt);
        }

        public int query(int L, int R, int l, int r,int rt){
            if (L <= l && r <= R){
                return max[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt,mid - l + 1,r - mid);
            int left = 0;
            int right = 0;
            if (L <= mid){
                left = query(L,R,l,mid,rt << 1);
            }
            if (R > mid) {
                right = query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return Math.max(left, right);
        }
    }
    // 做离散化 partitions 有可能传的值特别大
    public HashMap<Integer, Integer> index(int[][] positions) {
        TreeSet<Integer> pos = new TreeSet<>();
        for (int[] partition : positions) {
            pos.add(partition[0]);
            pos.add(partition[0] + partition[1] - 1);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (Integer index : pos) {
            map.put(index,++count);
        }
        return map;

    }
    public List<Integer> fallingSquares(int[][] positions) {
        HashMap<Integer, Integer> map = index(positions);
        int N = map.size();
        SegmentTree segmentTree = new SegmentTree(N);
        int max = 0;
        ArrayList<Integer> res = new ArrayList<>();

        for (int[] arr : positions) {
            int l = map.get(arr[0]);
            int r = map.get(arr[0] + arr[1] - 1);
            int height = segmentTree.query(l, r, 1, N, 1) + arr[1];
            max = Math.max(max,height);
            res.add(max);
            segmentTree.update(l, r, height, 1, N, 1);
        }
        return res;
    }
}
