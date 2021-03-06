package com.leetcode.www;

import java.util.*;

/**
 * Created by liyao on 7/12/17.
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph { // LC 323
    public int countComponents(int n, int[][] edges) { // beats 71.43%
        int[] root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
        for (int[] edge: edges) {
            int parent = findRoot(root, edge[0]);
            int child = findRoot(root, edge[1]);
            root[child] = parent;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (root[i] == i) {
                count++;
            }
        }
        return count;
    }

    private int findRoot(int[] root, int cur) {
        while (cur != root[cur]) {
            root[cur] = root[root[cur]];
            cur = root[cur];
        }
        return cur;
    }

    public int countComponentsV0(int n, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Set<Integer> nodes = new HashSet<>();

        for (int i = 0; i < edges.length; i++) {
            int first = edges[i][0], second = edges[i][1];
            if (!map.containsKey(first)) {
                List<Integer> list = new ArrayList<>();
                map.put(first, list);
            }
            if (!map.containsKey(second)) { // fix bug for test case 3
                List<Integer> list = new ArrayList<>();
                map.put(second, list);
            }
            map.get(first).add(second);
            map.get(second).add(first);
        }

        int result = 0;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int first = entry.getKey();
            if (nodes.add(first)) { // first is not in set yet
                result++;
                dfs(nodes, entry.getValue(), map);
            }
        }

        return result + (n - nodes.size()); // fix bug for test case 2
    }

    private void dfs(Set<Integer> nodes, List<Integer> list, Map<Integer, List<Integer>> map) {
        if (list != null) {
            for (int node : list) {
                if (nodes.add(node)) {
                    dfs(nodes, map.get(node), map);
                }
            }
        }
    }

    // 5, [[0,1],[1,2],[3,4]] => 2
    // 4, [[2,3],[1,2],[1,3]] => 2
    // 3, [[1,0],[2,0]] => 1

    // beats 13.41%
}

/**
 * Number of Connected Components in an Undirected Graph
 */
