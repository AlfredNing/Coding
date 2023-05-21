package stage03.lesson10;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import lombok.val;

/**
 * 直线最多的点
 *
 * @author Alfred.Ning
 * @since 2023年03月26日 11:42:00
 */
public class MaxPointOnOneLine {

  public static class Point {

    public int x;
    public int y;

    Point() {
      x = 0;
      y = 0;
    }

    Point(int a, int b) {
      x = a;
      y = b;
    }
  }

  public static int maxPoints(Point[] points) {
    if (points == null) {
      return 0;
    }
    if (points.length <= 2) {
      return points.length;
    }

    int result = 0;
    // 斜率表
    Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
    for (int i = 0; i < points.length; i++) {
      map.clear();
      int samePosition = 1;
      int sameX = 0;
      int sameY = 0;
      int line = 0;
      for (int j = i + 1; j < points.length; j++) {
        int x = points[j].x - points[i].x;
        int y = points[j].y - points[i].y;

        if (x == 0 && y == 0) {
          samePosition++;
        } else if (x == 0) {
          sameX++;
        } else if (y == 0) {
          sameY++;
        } else {
          int gcd = gcd(x, y);
          x /= gcd;
          y /= gcd;
          if (!map.containsKey(x)) {
            map.put(x, new HashMap<>());
          }
          if (!map.get(x).containsKey(y)) {
            map.get(x).put(y, 0);
          }
          map.get(x).put(y, map.get(x).get(y) + 1);
          line = Math.max(line, map.get(x).get(y));
        }
      }
      result = Math.max(result, samePosition + Math.max(line, Math.max(sameX, sameY)));
    }
    return result;
  }

  private static int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
  }

  public static void main(String[] args) {
    Point p1 = new Point(1, 1);
    Point p2 = new Point(2, 2);
    Point p3 = new Point(3, 3);
    // ---
    Point p4 = new Point(1, 0);
    Point p5 = new Point(0, 0);
    Point[] points1 = {p1, p2, p3};
//    System.out.println(maxPoints(points1));
    Point[] points2 = {p4, p5};
    System.out.println(maxPoints(points2));
  }

}
