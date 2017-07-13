import java.util.*;

/**
 * Created by liyao on 7/12/17.
 */
public class Solution {
    public int countComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Set<Integer> nodes = new HashSet<>();

        for (int i = 0; i < edges.length; i++) {
            int first = edges[i][0], second = edges[i][1];
            if (!map.containsKey(first)) {
                List<Integer> list = new ArrayList<>();
                map.put(first, list);
            }
            map.get(first).add(second);
        }

        int result = 0;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int first = entry.getKey();
            if (nodes.add(first)) { // first is not in set yet
                result++;
                dfs(nodes, entry.getValue(), map);
            }
        }

        return result;
    }

    private void dfs(Set<Integer> nodes, List<Integer> list, Map<Integer, List<Integer>> map) {
        for (int node : list) {
            if (nodes.add(node)) {
                dfs(nodes, map.get(node), map);
            }
        }
    }
}
