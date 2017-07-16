/**
 * Created by liyao on 7/15/17.
 */

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.PriorityQueue;

public class LFUCache { // HashMap + Doubly Linked List + LinkedHashSet, O(1)
    private int capacity;
    private Node head; // frequency (double linked) list which has keys list (linked hash set)
    private Map<Integer, Integer> valueMap;
    private Map<Integer, Node> nodeMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.head = null;
        this.valueMap = new HashMap<>();
        this.nodeMap = new HashMap<>();
    }

    public int get(int key) {
        if (valueMap.containsKey(key)) {
            increaseFrequency(key);
            return valueMap.get(key);
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        } else {
            if (valueMap.containsKey(key)) {
                valueMap.put(key, value);
            } else {
                if (valueMap.size() == capacity) {
                    removeOld();
                }
                valueMap.put(key, value);
                addToHead(key);
            }

            increaseFrequency(key);
        }
    }

    private void increaseFrequency(int key) {
        Node node = nodeMap.get(key);
        node.keys.remove(key);

        if (node.next == null) {
            Node nextNode = new Node(node.frequency + 1);
            nextNode.keys.add(key);
            nextNode.prev = node;
            node.next = nextNode;
        } else if (node.frequency + 1 == node.next.frequency) { // next node has (frequency + 1)
            node.next.keys.add(key);
        } else { // next node frequency is larger than current (frequency + 1)
            Node tmp = new Node(node.frequency + 1);
            tmp.keys.add(key);
            tmp.next = node.next;
            tmp.prev = node;
            node.next.prev = tmp;
            node.next = tmp;
        }

        nodeMap.put(key, node.next);
        if (node.keys.size() == 0) {
            remove(node);
        }
    }

    private void addToHead(int key) {
        if (head == null) {
            head = new Node(0);
            head.keys.add(key);
        } else if (head.frequency > 0) {
            Node node = new Node(0);
            node.keys.add(key);
            // update node as head
            node.next = head;
            head.prev = node;
            head = node;
        } else {
            head.keys.add(key);
        }

        nodeMap.put(key, head);
    }

    private void removeOld() { // remove lease frequently used element (key)
        if (head == null) {
            return;
        } else {
            int old = 0;
            for (int key : head.keys) {
                old = key; // find first (old) key
                break;
            }
            head.keys.remove(old);
            if (head.keys.size() == 0) {
                remove(head);
            }
            valueMap.remove(old);
            nodeMap.remove(old);
        }
    }

    private void remove(Node node) {
        if (node.prev == null) { // head
            head = node.next;
        } else { // non-head
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        node.prev = null;
        node.next = null;
    }

    class Node {
        private int frequency;
        LinkedHashSet<Integer> keys;
        Node prev;
        Node next;

        public Node(int frequency) {
            this.frequency = frequency;
            keys = new LinkedHashSet<>();
            prev = null;
            next = null;
        }
    }
}


class LFUCacheV0 { // HashMap + Heap (Priority Queue), O(logn)

    private int index;
    private int capacity;
    private Map<Integer, Data> map;
    private PriorityQueue<Data> minHeap;

    public LFUCacheV0(int capacity) {
        if (capacity == 0) {
            return;
        } else {
            map = new HashMap<>();
            minHeap = new PriorityQueue<>(capacity);
            this.capacity = capacity;
            this.index = 0;
        }
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }

        if (map.containsKey(key)) {
            index++;
            Data data = map.get(key);
            Data newData = new Data(key, data.value, data.frequency + 1, index);
            minHeap.remove(data);
            minHeap.offer(newData);
            data.frequency += 1;
            data.index = index;

            return data.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        index++;
        if (map.containsKey(key)) {
            Data data = map.get(key);
            minHeap.remove(data); // remove old data from heap first (test case 4)

            if (data.value == value) {
                data.frequency += 1;
            } else {
                data.frequency += 1;
                data.value = value;
            }

            Data newData = new Data(key, data.value, data.frequency, index);
            minHeap.add(newData);
        } else {
            if (map.size() == capacity) {
                Data lfuData = minHeap.poll();
                map.remove(lfuData.key);
            }
            Data data = new Data(key, value, 0, index);
            map.put(key, data);
            minHeap.offer(data);
        }
    }

    class Data implements Comparable<Data> {
        private int key;
        private int value;
        private int frequency;
        private int index;

        public Data(int key, int value, int frequency, int index) {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
            this.index = index;
        }

        public int compareTo(Data that) {
            if (this.frequency < that.frequency) {
                return -1;
            } else if (this.frequency == that.frequency) {
                if (this.index < that.index) {
                    return -1;
                } else if (this.index > that.index) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 1;
            }
        }

        @Override
        public boolean equals(Object object) {
            if (object instanceof Data) {
                Data that = (Data)object;
                if(this.key == that.key & this.value == that.value){
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        @Override
        public int hashCode(){
            return index;
        }
    }

    // ["LFUCache","put","put","get","put","get","get","put","get","get","get"], [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]] => [null,null,null,1,null,-1,3,null,-1,3,4]
    // ["LFUCache","put","get"], [[0],[0,0],[0]] => [null,null,-1]
    // ["LFUCache","put","put","get","put","get","get","put","get","get","get"],[[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]] => [null,null,null,2,1,2,null,null,-1,2,1,4]
    // ["LFUCache","put","put","put","put","get"],[[2],[3,1],[2,1],[2,2],[4,4],[2]] => [null,null,null,null,null,2]
    // ["LFUCache","put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"],[[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]] => [null,null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null,null,null,-1,null,-1,24,null,null,18,null,null,null,null,14,null,null,18,null,null,11,null,null,null,null,null,18,null,null,-1,null,4,29,30,null,12,11,null,null,null,null,29,null,null,null,null,17,-1,18,null,null,null,-1,null,null,null,20,null,null,null,29,18,18,null,null,null,null,20,null,null,null,null,null,null,null]
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */