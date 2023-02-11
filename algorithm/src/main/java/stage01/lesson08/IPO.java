package stage01.lesson08;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ClassName IPO
 * @Description 最大的收益
 * @Author Alfred.Ning
 * @Date 2022/5/21 15:24
 * @Version 1.0
 **/
public class IPO {
    public static class Program {
        public int p;
        public int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static int findMaximizedCapital(int k, int w, int[] profits, int[] costs) {
        // 小根堆
        PriorityQueue<Program> costMinQueue = new PriorityQueue<>(new MinCostComparator());
        // 大根堆 最大的收益
        PriorityQueue<Program> maxProfitQueue = new PriorityQueue<>(new MaxProfitComparator());
        
        // init 
        for (int i = 0; i < profits.length; i++) {
            costMinQueue.add(new Program(profits[i], costs[i]));
        }
        
        for (int i = 0; i < k; i++) {
            while (!costMinQueue.isEmpty() && costMinQueue.peek().c <= w) {
                // 这个项目可以做
                maxProfitQueue.add(costMinQueue.poll());
            }
            if(maxProfitQueue.isEmpty()){
                // 最大收益里面为空，返回初始资金
                return w;
            }
            w += maxProfitQueue.poll().p;
        }
        return w;
    }

    static class MinCostComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }
    }

    static class MaxProfitComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o2.c - o1.c;
        }
    }
}
