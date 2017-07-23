import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if (len == 0 || k == 0) {
            return new int[]{};
        } else if (k == 1) {
            return nums;
        }
        int[] results = new int[len - k + 1];
        Deque<Integer> queue = new ArrayDeque<>(); // index queue (descending order)

        for (int cur = 0; cur < results.length; cur++) {
            System.out.println("queue before: ");
            System.out.println(queue);

            removePassedNums(queue, cur);

            for (int offset = 0; offset < k && cur + offset < len; offset++) {
                int idx = cur + offset, curNum = nums[idx];
                System.out.println(idx + ", " + curNum);
                if (queue.isEmpty() || curNum < nums[queue.getLast()]) { System.out.println("[without remove] add last: " + idx);
                    queue.addLast(idx);
                } else {
                    while (!queue.isEmpty() && curNum >= nums[queue.getLast()]) {
                        queue.removeLast();
                    }
                    queue.addLast(idx); System.out.println("[after remove] add last: " + idx);
                }
            }
            System.out.println("queue after: ");
            System.out.println(queue);
            if (!queue.isEmpty()) {
                results[cur] = nums[queue.getFirst()];
            }
        }

        return results;
    }

    private void removePassedNums(Deque<Integer> queue, int curIdx) {
        while (!queue.isEmpty() && queue.getFirst() < curIdx) {
            queue.removeFirst();
        }
    }

    // [],0 => []
    // [1,3,-1,-3,5,3,6,7],3 => [3,3,5,5,6,7]
    // [5,3,4],1 => [5,3,4]
}
