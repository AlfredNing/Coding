package stage02.lesson06;

/**
 * @ClassName ZigZagPrintMatrix
 * @Description 锯齿形打印矩阵
 * @Author Alfred.Ning
 * @Date 8/27/2022 9:59 PM
 * @Version 1.0
 **/
public class ZigZagPrintMatrix {
    public static void printMatrixZigZag(int[][] matrix) {
        int aR = 0; // 左上角A起始行点
        int aC = 0; // 左上角A起始列点
        int bR = 0; // 左上角B起始行点
        int bC = 0; // 左上角B起始列点
        int endR = matrix.length - 1;// 右下角结束的行点
        int endC = matrix[0].length - 1;// 右下角结束的行点
        boolean fromUp = false; // 表示是否从右上往左下打印
        while (aR != endR + 1) {
            printLevel(matrix, aR, aC, bR, bC, fromUp);
            // A点移动
            aR = aC == endC ? aR + 1 : aR;
            aC = aC == endC ? aC : aC + 1;

            // B点移动 先移动列坐标
            bC = bR == endR ? bC + 1 : bC;
            bR = bR == endR ? bR : bR + 1;
            fromUp = !fromUp;
        }
        System.out.println();
    }

    private static void printLevel(int[][] matrix, int aR, int aC, int bR, int bC, boolean fromUp) {
        if (fromUp) {
            while (aR != bR + 1) {
                System.out.print(matrix[aR++][aC--] + " ");
            }
        } else {
            while (bR != aR - 1) {
                System.out.print(matrix[bR--][bC++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        printMatrixZigZag(matrix);

    }
}
