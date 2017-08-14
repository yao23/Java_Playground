package com.leetcode.www;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph { // LC 133

    // time: O(V + 2E), space: O(V)
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) { // beats 71.08% (DFS)
        if (node == null) {
            return node;
        }
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        return dfsHelper(node, map);
    }

    private UndirectedGraphNode dfsHelper(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> map) {
        // 1. create node and put into map
        map.put(node, new UndirectedGraphNode(node.label));
        // 2. create all edges starting from current copied
        for (UndirectedGraphNode neighbor : node.neighbors) {
            UndirectedGraphNode newNeighbor = map.get(neighbor);
            // create with DFS if not exist
            if (newNeighbor == null) {
                newNeighbor = dfsHelper(neighbor, map);
            }
            map.get(node).neighbors.add(newNeighbor);
        }

        return map.get(node);
    }

    class UndirectedGraphNode {
      int label;
      List<UndirectedGraphNode> neighbors;
      UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    }
}
