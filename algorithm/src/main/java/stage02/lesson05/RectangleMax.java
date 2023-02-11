package stage02.lesson05;

import java.util.*;

/**
 * @ClassName RecantageMax
 * @Description 矩形面积重合问题
 * @Author Alfred.Ning
 * @Date 8/21/2022 9:58 PM
 * @Version 1.0
 **/
public class RectangleMax {
    public static class Rectangle{
        public int up;
        public int down;
        public int left;
        public int right;

        public Rectangle(int up, int down, int left, int right) {
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
        }
    }

    public static int maxCover(Rectangle[] recs) {
        if (recs == null || recs.length == 0){
            return 0;
        }
        // 根据矩形最底边排序
        Arrays.sort(recs, Comparator.comparingInt(o -> o.down));
        // 对当前底边的公共区域，产生影响的矩形 保证从左往右
        TreeSet<Rectangle> leftOrdered = new TreeSet<>(Comparator.comparingInt(o -> o.left));
        int ans = 0;

        // o(N)
        // 考察每个矩形的底边
        for (int i = 0; i < recs.length; i++) {
            int curDown = recs[i].down;
            int index = i;
            // 小优化 相同共底线的矩形先加进来
            while (recs[index].down == curDown) {
                leftOrdered.add(recs[index]);
                index++;
            }
            i = index;

            // 把某些矩形一定顶部<= 当前底的矩形 删掉
            removeLowerOnCurDown(leftOrdered, curDown);
            // 线段树右边界
            TreeSet<Rectangle> rightOrdered = new TreeSet<>(Comparator.comparingInt(o -> o.right));
            for (Rectangle rec : leftOrdered) {
                removeLeftOnCurLef(rightOrdered,rec.left);
                rightOrdered.add(rec);
                ans = Math.max(ans,rightOrdered.size());
            }
        }

        return ans;
    }

    private static void removeLeftOnCurLef(TreeSet<Rectangle> rightOrdered, int left) {
        ArrayList<Rectangle> removes = new ArrayList<>();
        for (Rectangle rectangle : rightOrdered) {
            if (rectangle.right > left){
                 break;
            }
            removes.add(rectangle);
        }
        for (Rectangle rec : removes) {
            rightOrdered.remove(rec);
        }
    }

    private static void removeLowerOnCurDown(TreeSet<Rectangle> set, int curDown) {
        ArrayList<Rectangle> removes = new ArrayList<>();
        for (Rectangle rectangle : set) {
            if (rectangle.down <= curDown){
                removes.add(rectangle);
            }
        }
        for (Rectangle rec : removes) {
            set.remove(rec);
        }
    }

}
