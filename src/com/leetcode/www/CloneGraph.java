package com.leetcode.www;

import java.util.*;

public class CloneGraph { // LC 133 - Graph traversal (DFS/BFS + HashMap)
    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Clone Graph.
     * Memory Usage: 32.5 MB, less than 98.91% of Java online submissions for Clone Graph.
     *
     * time: O(V + 2E), space: O(V)
     *
     * @param node
     * @return
     */
    public Node cloneGraphV0(Node node) { // DFS
        if (node == null) {
            return node;
        }
        Map<Node, Node> map = new HashMap<>();
        return dfsHelper(node, map);
    }

    private Node dfsHelper(Node node, Map<Node, Node> map) {
        // 1. create node and put into map
        map.put(node, new Node(node.val, new ArrayList<>()));
        // 2. create all edges starting from current copied
        for (Node neighbor : node.neighbors) {
            Node newNeighbor = map.get(neighbor);
            // create with DFS if not exist
            if (newNeighbor == null) {
                newNeighbor = dfsHelper(neighbor, map);
            }
            map.get(node).neighbors.add(newNeighbor);
        }

        return map.get(node);
    }

    /**
     * Runtime: 2 ms, faster than 63.73% of Java online submissions for Clone Graph.
     * Memory Usage: 33 MB, less than 98.87% of Java online submissions for Clone Graph.
     *
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) { // BFS
        if (node == null) {
            return node;
        }

        return bfsHelper(node);
    }

    private Node bfsHelper(Node node) {
        Map<Node, Node> map = new HashMap<>();
        Deque<Node> queue = new ArrayDeque<>();

        queue.offerLast(node);
        map.put(node, new Node(node.val, null));

        while (!queue.isEmpty()) {
            Node cur = queue.pollFirst();
            Node copy = map.get(cur);
            copy.neighbors = new ArrayList<>();
            for (Node neighbor : cur.neighbors) {
                Node newNeighbor = map.get(neighbor);
                if (newNeighbor == null) {
                    // 1. create node, put into map & queue
                    queue.offerLast(neighbor);
                    map.put(neighbor, new Node(neighbor.val, null));
                }
                // 2. create edge starting from current copied
                copy.neighbors.add(map.get(neighbor));
            }
        }

        return map.get(node);
    }

    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
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