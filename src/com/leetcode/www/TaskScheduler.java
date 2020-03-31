import java.util.Arrays;

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
}
