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

/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


 * OJ's undirected graph serialization:
 * Nodes are labeled uniquely.

 * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.

 * The graph has a total of three nodes, and therefore contains three parts as separated by #.

 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 * Visually, the graph looks like the following:
 *
 *        1
 *       / \
 *      /   \
 *     0 --- 2
 *          / \
 *          \_/
 */