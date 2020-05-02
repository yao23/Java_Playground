import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class OpenTheLock { // 752
    /**
     * BFS
     * Time Complexity: O(N^2 * {A}^N + D) where A is the number of digits in our alphabet, N is the number of digits
     * in the lock, and D is the size of deadends. We might visit every lock combination, plus we need to instantiate
     * our set dead. When we visit every lock combination, we spend O(N^2) time enumerating through and constructing each node.
     *
     * Space Complexity: O(A^N + D), for the queue and the set dead.
     *
     */
    class Solution {
        public int openLock(String[] deadends, String target) {
            Set<String> dead = new HashSet();
            for (String d: deadends) dead.add(d);

            Queue<String> queue = new LinkedList();
            queue.offer("0000");
            queue.offer(null);

            Set<String> seen = new HashSet();
            seen.add("0000");

            int depth = 0;
            while (!queue.isEmpty()) {
                String node = queue.poll();
                if (node == null) {
                    depth++;
                    if (queue.peek() != null)
                        queue.offer(null);
                } else if (node.equals(target)) {
                    return depth;
                } else if (!dead.contains(node)) {
                    for (int i = 0; i < 4; ++i) {
                        for (int d = -1; d <= 1; d += 2) {
                            int y = ((node.charAt(i) - '0') + d + 10) % 10;
                            String nei = node.substring(0, i) + ("" + y) + node.substring(i+1);
                            if (!seen.contains(nei)) {
                                seen.add(nei);
                                queue.offer(nei);
                            }
                        }
                    }
                }
            }
            return -1;
        }
    }
}
