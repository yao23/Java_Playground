package com.leetcode.www; /**
 * Created by liyao on 7/11/17.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;

public class LRUCache { // LC 146 (Facebook, TikTok)
    /**
     * Runtime: 63 ms, faster than 75.45% of Java online submissions for LRU Cache.
     * Memory Usage: 57.1 MB, less than 74.20% of Java online submissions for LRU Cache.
     *
     * HashMap + Double LinkedList
     *
     */
    class Node {
        int key;
        int value;
        Node prev; // easy to get last node (least recently used)
        Node next;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private int capacity;
    private int numNode;
    private Node head; // easy to add node in head
    private Node tail; // easy to remove last node when over capacity
    private Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.numNode = 0;
        this.capacity = capacity;
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.map = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        } else {
            // remove from doubly linked list
            Node node = removeFromList(key);
            // add to the head in doubly linked list
            addToHead(node);

            return node.value;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) { // if contains then remove and put in head
            Node node = removeFromList(key);
            if (node.value != value) { // test case 2 to put duplicate key but different value
                node.value = value;
            }
            addToHead(node);
        } else { // otherwise remove last one if ove capacity and insert in head
            if (numNode >= capacity) {
                int lastKey = this.tail.prev.key;
                removeFromList(lastKey);
                map.remove(lastKey);
            } else {
                numNode++;
            }

            Node node = new Node(key, value);
            addToHead(node);
            map.put(key, node);
        }
    }

    private Node removeFromList(int key) {
        Node cur = map.get(key);
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        cur.prev = null;
        cur.next = null; // break cur node from list
        return cur;
    }

    private void addToHead(Node node) {
        node.next = this.head.next;
        node.prev = this.head;
        this.head.next = node;
        node.next.prev = node;
    }

    // ["com.leetcode.www.LRUCache","put","put","get","put","get","put","get","get","get"], [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]] => [null,null,null,1,null,-1,null,-1,3,4]
    // ["com.leetcode.www.LRUCache","put","put","get","put","put","get"], [[2],[2,1],[2,2],[2],[1,1],[4,1],[2]] => [null,null,null,2,null,null,-1]

    // beats 49.65%
}

/**
 * Runtime: 67 ms, faster than 45.80% of Java online submissions for LRU Cache.
 * Memory Usage: 56 MB, less than 78.77% of Java online submissions for LRU Cache.
 *
 * LinkedHashMap
 */
class LRUCacheV0 {
    public int cap;
    private LinkedHashMap<Integer, Integer> lru;
    public LRUCacheV0(int capacity) {
        this.lru = new LinkedHashMap<>();
        cap = capacity;
    }

    public int get(int key) {
        if (lru.containsKey(key)) {
            int new_value = lru.get(key);
            lru.remove(key);
            lru.put(key,new_value);
            return new_value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (lru.containsKey(key)) {
            lru.remove(key);
        } else if(lru.size() >= cap) {
            for(int k:lru.keySet()) {
                lru.remove(k); // remove last node (least recently used)
                break;
            }
        }
        lru.put(key,value);
    }
}

/**
 * Your com.leetcode.www.LRUCache object will be instantiated and called as such:
 * com.leetcode.www.LRUCache obj = new com.leetcode.www.LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
