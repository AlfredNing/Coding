package stage03.lesson15;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 喝咖啡的最早时间
 *
 * @author Alfred.Ning
 * @since 2023年05月28日 12:06:00
 */
public class CoffeeMachineTime {

  public static class CoffMachine {

    public int start;
    public int work;

    public CoffMachine(int start, int work) {
      this.start = start;
      this.work = work;
    }
  }

  public static class MachineComparator implements Comparator<CoffMachine> {

    @Override
    public int compare(CoffMachine o1, CoffMachine o2) {
      return o1.start + o1.work - o2.start - o2.work;
    }

  }

  public static int[] bestChoice(int[] arr, int m) {
    int[] res = new int[m];
    PriorityQueue<CoffMachine> heap = new PriorityQueue<>(new MachineComparator());
    for (int workStart : arr) {
      heap.add(new CoffMachine(0, workStart));
    }

    for (int i = 0; i < m; i++) {
      CoffMachine machine = heap.poll();
      res[i] = machine.start + machine.work;
      machine.start = res[i];
      heap.add(machine);
    }
    return res;
  }
}
