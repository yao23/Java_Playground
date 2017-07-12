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
        System.out.println("before get key " + key);
        printList();
        System.out.println(map);
        if (map.keySet().contains(key)) {
            System.out.println("found " + key);
        } else {
            System.out.println("not found " + key);
        }
        if (!map.containsKey(key)) { System.out.println("not found " + key); // map.get(key) == null
            return -1;
        } else {
            // remove from doubly linked list
            Node node = removeFromList(key);
            // add to the head in doubly linked list
            addToHead(node);
            System.out.println("after get key " + key);
            printList();
            return node.value;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) { // if contains then remove and put in head
            System.out.println("before put (exist): " + key);
            printList();
            Node node = removeFromList(key);
            addToHead(node);
            System.out.println("after put (exist)");
            printList();
        } else { // otherwise remove last one if ove capacity and insert in head
            System.out.println("before put (not exist): " + key);
            printList();
            if (numNode >= capacity) {
                int lastKey = this.tail.prev.key;
                removeFromList(lastKey);
                map.remove(lastKey);
                System.out.println("after remove (over capacity)");
                printList();
            } else {
                numNode++;
            }

            Node node = new Node(key, value);
            addToHead(node);
            map.put(key, node);

            System.out.println("after put (not exist)");
            printList();
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

    private void printList() {
        System.out.println();

        Node node = this.head;
        while (node != null) {
            System.out.print(node.key + ", ");
            node = node.next;
        }

        System.out.println();
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */