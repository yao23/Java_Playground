/**
 * Created by liyao on 7/11/17.
 */

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class Node {
        int key;
        int value;
        Node prev;
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
    private Node head;
    private Node tail;
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

    // ["LRUCache","put","put","get","put","get","put","get","get","get"], [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]] => [null,null,null,1,null,-1,null,-1,3,4]
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */