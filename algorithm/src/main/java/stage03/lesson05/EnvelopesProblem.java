package stage03.lesson05;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 信封嵌套问题.
 *
 * @author Alfred.Ning
 */
public class EnvelopesProblem {
    public static class Envelope {
        public int w;
        public int h;

        public Envelope(int width, int height) {
            this.w = width;
            this.h = height;
        }
    }

    public static class EnvelopComparator implements Comparator<Envelope> {
        @Override
        public int compare(Envelope o1, Envelope o2) {
            return o1.w != o2.w ? o1.w - o2.w : o2.h - o1.h;
        }
    }

    public static Envelope[] sort(int[][] matrix) {
        Envelope[] res = new Envelope[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            res[i] = new Envelope(matrix[i][0], matrix[i][1]);
        }
        Arrays.sort(res, new EnvelopComparator());
        return res;
    }

    public static int maxEnvelopes(int[][] matrix) {
        Envelope[] arr = sort(matrix);
        int[] ends = new int[arr.length];
        ends[0] = arr[0].h;
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        for (int i = 0; i < arr.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i].h > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i].h;
        }
        return right + 1;
    }
}
