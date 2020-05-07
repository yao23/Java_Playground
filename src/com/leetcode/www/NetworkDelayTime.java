import java.util.*;

public class NetworkDelayTime { // 743 [Google]
    Map<Integer, Integer> dist;

    /**
     * Dijkstra's Algorithm
     *
     * Time Complexity: O(N^2 + E) where E is the length of times in the basic implementation.
     * Space Complexity: O(N + E), the size of the graph (O(E)), plus the size of the other objects used (O(N)).
     *
     * Runtime: 12 ms, faster than 86.01% of Java online submissions for Network Delay Time.
     * Memory Usage: 43.4 MB, less than 90.48% of Java online submissions for Network Delay Time.
     *
     * @param times
     * @param N
     * @param K
     * @return
     */
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge: times) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<>());
            }
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        dist = new HashMap<>();
        for (int node = 1; node <= N; ++node) {
            dist.put(node, Integer.MAX_VALUE);
        }

        dist.put(K, 0);
        boolean[] seen = new boolean[N+1];

        while (true) {
            int candNode = -1;
            int candDist = Integer.MAX_VALUE;
            for (int i = 1; i <= N; ++i) {
                if (!seen[i] && dist.get(i) < candDist) {
                    candDist = dist.get(i);
                    candNode = i;
                }
            }

            if (candNode < 0) break;
            seen[candNode] = true;
            if (graph.containsKey(candNode)) {
                for (int[] info : graph.get(candNode)) {
                    dist.put(info[0],
                            Math.min(dist.get(info[0]), dist.get(candNode) + info[1]));
                }
            }
        }

        int ans = 0;
        for (int cand: dist.values()) {
            if (cand == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, cand);
        }
        return ans;
    }

    /**
     * Time Complexity: O(ElogE) in the heap implementation, as potentially every edge gets added to the heap.
     * Space Complexity: O(N + E), the size of the graph (O(E)), plus the size of the other objects used (O(N))
     *
     * Runtime: 19 ms, faster than 61.01% of Java online submissions for Network Delay Time.
     * Memory Usage: 42.8 MB, less than 92.86% of Java online submissions for Network Delay Time.
     *
     * @param times
     * @param N
     * @param K
     * @return
     */
    public int networkDelayTimeV2(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge: times) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<>());
            }
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>(
                Comparator.comparingInt(info -> info[0]));
        heap.offer(new int[]{0, K});

        Map<Integer, Integer> dist = new HashMap<>();

        while (!heap.isEmpty()) {
            int[] info = heap.poll();
            int d = info[0], node = info[1];
            if (dist.containsKey(node)) {
                continue;
            }
            dist.put(node, d);
            if (graph.containsKey(node)) {
                for (int[] edge : graph.get(node)) {
                    int nei = edge[0], d2 = edge[1];
                    if (!dist.containsKey(nei)) {
                        heap.offer(new int[]{d + d2, nei});
                    }
                }
            }
        }

        if (dist.size() != N) {
            return -1;
        }
        int ans = 0;
        for (int cand: dist.values())
            ans = Math.max(ans, cand);
        return ans;
    }

    /**
     * Time Complexity: O(N^N + E*logE) where E is the length of times. We can only fully visit each node up to N-1 times,
     * one per each other node. Plus, we have to explore every edge and sort them. Sorting each small bucket of outgoing
     * edges is bounded by sorting all of them, because of repeated use of the inequality xlogx + ylogy <= (x+y)log(x+y).
     *
     * Space Complexity: O(N + E), the size of the graph (O(E)), plus the size of the implicit call stack in our
     * DFS (O(N)).
     *
     * Runtime: 47 ms, faster than 18.20% of Java online submissions for Network Delay Time.
     * Memory Usage: 42.3 MB, less than 97.62% of Java online submissions for Network Delay Time.
     *
     * @param times
     * @param N
     * @param K
     * @return
     */
    public int networkDelayTimeV1(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge: times) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<>());
            }
            graph.get(edge[0]).add(new int[]{edge[2], edge[1]});
        }
        for (int node: graph.keySet()) {
            graph.get(node).sort(Comparator.comparingInt(a -> a[0]));
        }
        dist = new HashMap<>();
        for (int node = 1; node <= N; ++node) {
            dist.put(node, Integer.MAX_VALUE);
        }

        dfs(graph, K, 0);
        int ans = 0;
        for (int cand: dist.values()) {
            if (cand == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, cand);
        }
        return ans;
    }

    public void dfs(Map<Integer, List<int[]>> graph, int node, int elapsed) {
        if (elapsed >= dist.get(node)) {
            return;
        }
        dist.put(node, elapsed);
        if (graph.containsKey(node)) {
            for (int[] info : graph.get(node)) {
                dfs(graph, info[1], elapsed + info[0]);
            }
        }
    }
    /**
     * Not work for test case
     * [[15,8,1],[7,10,41],[7,9,34],[9,4,31],[12,13,50],[14,3,52],[4,11,99],[4,7,86],[10,13,57],[9,6,10],[1,7,51],
     * [7,15,38],[1,9,11],[12,7,94],[9,13,34],[11,7,79],[7,6,28],[5,3,34],[2,6,97],[14,1,97],[6,10,90],[12,10,37],
     * [13,3,73],[11,14,7],[15,1,39],[6,5,90],[13,6,43],[6,9,32],[4,6,45],[11,10,2],[2,13,4],[14,15,29],[1,14,88],
     * [14,6,19],[6,2,29],[3,14,72],[1,15,4],[11,5,2],[6,7,56],[8,7,88],[13,14,70],[14,12,58],[14,2,86],[11,3,57],
     * [5,2,56],[3,10,26],[2,11,21],[14,5,54],[5,12,40],[14,4,81],[15,2,99],[5,7,57],[13,12,5],[4,9,60],[12,15,48],
     * [6,14,1],[9,7,44],[13,7,69],[5,13,42],[4,1,7],[11,9,76],[8,1,76],[5,14,29],[2,3,69],[7,3,23],[12,14,28],
     * [11,4,85],[10,1,10],[15,12,36],[1,11,69],[15,10,96],[11,13,69],[7,12,49],[1,2,95],[6,4,46],[8,12,94],
     * [12,4,93],[13,5,31],[12,2,60],[6,1,87],[4,14,20],[5,11,89],[4,15,88],[4,10,21],[1,6,5],[10,8,26],[8,2,51],
     * [3,15,23],[7,2,12],[11,1,47],[2,1,75],[3,8,63],[8,10,19],[6,8,18],[4,2,55],[14,11,80],[10,3,73],[3,5,22],
     * [12,3,61],[1,13,33],[9,3,98],[9,12,69],[15,9,6],[7,13,76],[11,12,22],[11,15,51],[13,15,46],[5,10,58],[1,10,26],
     * [13,4,85],[7,14,58],[5,8,46],[11,6,32],[10,9,41],[9,14,35],[14,13,60],[3,9,97],[2,5,39],[7,11,19],[1,12,27],
     * [7,5,13],[8,4,34],[9,15,25],[5,1,93],[15,13,97],[14,9,35],[8,6,67],[9,5,39],[13,11,35],[7,4,21],[12,9,64],
     * [14,8,8],[10,12,94],[8,9,76],[8,5,71],[2,9,64],[10,14,59],[1,4,74],[7,1,69],[15,5,55],[6,15,80],[13,8,84],
     * [8,13,63],[8,3,91],[10,4,87],[1,5,39],[8,11,0],[1,3,79],[4,5,82],[4,12,87],[3,11,29],[7,8,92],[10,7,77],
     * [6,12,42],[13,2,40],[9,10,13],[4,13,65],[2,4,34],[3,13,44],[2,14,69],[3,4,42],[5,15,98],[14,7,6],[15,3,94],
     * [10,2,37],[15,11,7],[9,2,15],[13,9,66],[4,8,83],[8,15,23],[13,1,50],[6,13,57],[2,10,37],[10,6,38],[2,7,45],
     * [9,8,8],[3,12,28],[3,2,83],[2,12,75],[1,8,91],[4,3,70],[12,6,48],[3,1,13],[5,6,42],[6,11,96],[3,6,22],[15,6,34],
     * [11,8,43],[15,7,40],[9,11,57],[11,2,11],[2,8,22],[9,1,73],[2,15,40],[12,11,10],[15,4,78],[12,8,75],[10,15,37],
     * [13,10,44],[8,14,33],[3,7,82],[5,4,46],[12,5,79],[15,14,43],[10,5,65],[5,9,34],[12,1,54],[6,3,16],[14,10,83],
     * [10,11,67]]
     * 15
     * 8
     * @param times
     * @param N
     * @param K
     * @return
     */
    public int networkDelayTimeV0(int[][] times, int N, int K) {
        class DelayComparator implements Comparator<int[]>{
            public int compare(int[] s1, int[] s2) {
                if (s1[1] < s2[1]) {
                    return -1;
                } else if (s1[1] > s2[1]) {
                    return 1;
                } else {
                    return Integer.compare(s1[0], s2[0]);
                }
            }
        }

        int count = 0, delayMax = 0;
        Map<Integer, PriorityQueue<int[]>> graph = new HashMap<>();
        Map<Integer, Integer> delayMap = new HashMap<>();
        for (int[] time : times) {
            int s = time[0], e = time[1], delay = time[2];
            if (!graph.containsKey(s)) {
                graph.put(s, new PriorityQueue<>(new DelayComparator()));
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
                    PriorityQueue<int[]> neighbors = graph.get(cur);
                    while (!neighbors.isEmpty()) {
                        int[] neighborNode = neighbors.poll();
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
