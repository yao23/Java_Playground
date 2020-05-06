import java.util.*;

public class NetworkDelayTime { // 743 [Google]
    public int networkDelayTime(int[][] times, int N, int K) {
        int count = 0, delayMax = 0;
        Map<Integer, List<int[]>> graph = new HashMap<>();
        Map<Integer, Integer> delayMap = new HashMap<>();
        for (int[] time : times) {
            int s = time[0], e = time[1], delay = time[2];
            if (!graph.containsKey(s)) {
                graph.put(s, new ArrayList<>());
            }
            graph.get(s).add(new int[]{e, delay});
        }
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(K);
        delayMap.put(K, 0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            count += size;
            for (int i = 0; i < size; i++) {
                int cur = queue.removeFirst();
                visited.add(cur);
                if (graph.containsKey(cur)) {
                    List<int[]> neighbors = graph.get(cur);
                    for (int[] neighborNode : neighbors) {
                        int neighbor = neighborNode[0], delay = neighborNode[1];
                        int neighborDelay = delayMap.get(cur) + delay;
                        if (visited.add(neighbor)) {
                            queue.offer(neighbor);
                            delayMap.put(neighbor, neighborDelay);
                        } else {
                            if (delayMap.get(neighbor) > neighborDelay) {
                                delayMap.put(neighbor, neighborDelay);
                            }
                        }
                    }
                }
            }
        }

        if (count == N) {
            for (Integer delay : delayMap.values()) {
                if (delay > delayMax) {
                    delayMax = delay;
                }
            }
            return delayMax;
        } else {
            return -1;
        }
    }
}
