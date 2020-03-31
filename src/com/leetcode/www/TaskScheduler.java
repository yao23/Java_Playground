import java.util.*;

public class TaskScheduler { // 621
    /**
     * Runtime: 5 ms, faster than 62.94% of Java online submissions for Task Scheduler.
     * Memory Usage: 41.4 MB, less than 5.88% of Java online submissions for Task Scheduler.
     *
     * Time complexity : O(time). Number of iterations will be equal to resultant time time.
     *
     * Space complexity : O(1). Constant size array map is used.
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int time = 0;
        while (map[25] > 0) {
            int i = 0;
            while (i <= n) {
                if (map[25] == 0)
                    break;
                if (i < 26 && map[25 - i] > 0)
                    map[25 - i]--;
                time++;
                i++;
            }
            Arrays.sort(map);
        }
        return time;
    }

    /**
     * Runtime: 28 ms, faster than 26.86% of Java online submissions for Task Scheduler.
     * Memory Usage: 40.8 MB, less than 10.29% of Java online submissions for Task Scheduler.
     *
     * Time complexity : O(n). Number of iterations will be equal to resultant time time.
     * Space complexity : O(1). queue and temp size will not exceed O(26).
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastIntervalV1(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        PriorityQueue < Integer > queue = new PriorityQueue< >(26, Collections.reverseOrder());
        for (int f: map) {
            if (f > 0)
                queue.add(f);
        }
        int time = 0;
        while (!queue.isEmpty()) {
            int i = 0;
            List< Integer > temp = new ArrayList< >();
            while (i <= n) {
                if (!queue.isEmpty()) {
                    if (queue.peek() > 1)
                        temp.add(queue.poll() - 1);
                    else
                        queue.poll();
                }
                time++;
                if (queue.isEmpty() && temp.size() == 0)
                    break;
                i++;
            }
            for (int l: temp)
                queue.add(l);
        }
        return time;
    }

    /**
     * Runtime: 2 ms, faster than 99.59% of Java online submissions for Task Scheduler.
     * Memory Usage: 41.6 MB, less than 5.88% of Java online submissions for Task Scheduler.
     *
     * Time complexity : O(n). We iterate over tasks array only once. (O(n)).
     * Sorting tasks array of length nn takes O(26log(26))=O(1) time. After this, only one iteration over 26 elements
     * of map is done (O(1)).
     *
     * Space complexity : O(1). map array of constant size(26) is used.
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastIntervalV2(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int max_val = map[25] - 1, idle_slots = max_val * n;
        for (int i = 24; i >= 0 && map[i] > 0; i--) {
            idle_slots -= Math.min(map[i], max_val);
        }
        return idle_slots > 0 ? idle_slots + tasks.length : tasks.length;
    }
}
