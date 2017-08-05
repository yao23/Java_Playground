import java.util.*;

public class MinimumHeightTrees { // LC 310
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // all leaves move to center together, last two or one converged node is the root
        // even as 2, odd as 1 for root

        List<Integer> res = new ArrayList<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Map<Integer, Integer> graph = new HashMap<>();
        int row = edges.length;
        if (row == 0) {
            return res;
        }

        // add neighbor list for each node
        for (int i = 0; i < row; i++) {
            int first = edges[i][0], second = edges[i][1];
            updateMap(map, first, second);
            updateMap(map, second, first);
        }

        // for (int i = 0; i < row; i++) {
        //     int first = edges[i][0], second = edges[i][1];
        //     updateGraphStart(graph, first);
        //     updateGraphEnd(graph, second);
        // }

        // add nodes in graph and init degree with 1
        for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
            for (Integer elem : entry.getValue()) {
                updateGraphEnd(graph, elem);
            }
        }

        // Set<Integer> visited = new  HashSet<>();
        Deque<Integer> q = new ArrayDeque<>();
        // start from each node with in-dgree 0 to neighbors, stop when queue has less than 2 nodes
        for (Map.Entry<Integer, Integer> entry : graph.entrySet()) {
            if (entry.getValue() == 1) {
                q.offer(entry.getKey());
            }
        }

        int counter = graph.size();
        while (counter > 2) {
            int cur = q.remove();
            // visited.add(cur);
            counter--;
            Set<Integer> neighbors = map.get(cur);
            if (neighbors != null) {
                for (Integer neighbor : neighbors) {
                    graph.put(neighbor, graph.get(neighbor) - 1);
                    if (graph.get(neighbor) == 0) {
                        q.offer(neighbor);
                    }
                }
            }
        }

        for (Integer elem : q) {
            res.add(elem);
        }

        return res;
    }

    private void updateMap(Map<Integer, Set<Integer>> map, int first, int second) {
        if (!map.containsKey(first)) {
            map.put(first, new HashSet<>());
        }
        map.get(first).add(second);
    }

    private void updateGraphStart(Map<Integer, Integer> graph, int elem) {
        if (!graph.containsKey(elem)) {
            graph.put(elem, 0); // in-degree default as 0 for start node
        }
    }

    private void updateGraphEnd(Map<Integer, Integer> graph, int elem) {
        if (graph.containsKey(elem)) {
            graph.put(elem, graph.get(elem) + 1);
        } else {
            graph.put(elem, 1); // in-degree default as 1 for end node
        }
    }
}

// n = 4, edges = [[1, 0], [1, 2], [1, 3]] => [1]
// n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]] => [3, 4]
