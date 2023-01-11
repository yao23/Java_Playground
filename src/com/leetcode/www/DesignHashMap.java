package com.leetcode.www;

public class DesignHashMap { // LC 706
    /**
     * Runtime: 62 ms, faster than 98.10% of Java online submissions for Design HashMap.
     * Memory Usage: 50.4 MB, less than 93.88% of Java online submissions for Design HashMap.
     *
     * https://leetcode.com/problems/design-hashmap/discuss/282737/Java-solution-using-array-and-singly-linked-list-beats-97-runtime
     */
    class MyHashMap {

        ListNode[] array;
        int capacity = 1000;

        /** Initialize your data structure here. */
        public MyHashMap() {
            array = new ListNode[capacity];
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            int index = key % capacity;
            ListNode node = new ListNode(key, value);
            ListNode current = array[index];
            ListNode previous = null;
            boolean isOverridden = false;
            while (current != null) {
                if (current.key == key) {
                    current.val = value;
                    isOverridden = true;
                    break;
                }
                previous = current;
                current = current.next;
            }
            if (!isOverridden) {
                if (previous == null) {
                    array[index] = node;
                } else {
                    previous.next = node;
                }
            }
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int index = key % capacity;
            ListNode current = array[index];
            while (current != null) {
                if (current.key == key) {
                    return current.val;
                }
                current = current.next;
            }
            return -1;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int index = key % capacity;
            ListNode current = array[index];
            ListNode previous = null;
            while (current != null) {
                if (current.key == key) {
                    if (previous == null) {
                        array[index] = current.next;
                    } else {
                        previous.next = current.next;
                    }
                    break;
                }
                previous = current;
                current = current.next;
            }
        }

        private class ListNode {
            int key;
            int val;
            ListNode next;

            ListNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
    }

    /**
     * Your MyHashMap object will be instantiated and called as such:
     * MyHashMap obj = new MyHashMap();
     * obj.put(key,value);
     * int param_2 = obj.get(key);
     * obj.remove(key);
     */

    class MyHashMapV1 {

        /**
         * Runtime: 64 ms, faster than 87.94% of Java online submissions for Design HashMap.
         * Memory Usage: 49.6 MB, less than 94.69% of Java online submissions for Design HashMap.
         *
         * https://leetcode.com/problems/design-hashmap/discuss/270685/Java-Separate-Chaining-with-rehashing
         *
         * Separate Chaining with rehashing
         */
        private static final double LOAD_FACTOR = 0.75;
        private Node[] nodes;
        private int size; // number of keys

        /** Initialize your data structure here. */
        public MyHashMapV1() {
            nodes = new Node[5];
            size = 0;
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            int idx = hash(key);
            for (Node x = nodes[idx]; x != null; x = x.next) {
                if (x.key == key) {
                    x.value = value;
                    return;
                }
            }
            nodes[idx] = new Node(key, value, nodes[idx]);
            size++;

            double loadFactor = (double) size / nodes.length;
            if (loadFactor > LOAD_FACTOR) {
                rehash();
            }
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int idx = hash(key);
            for (Node x = nodes[idx]; x != null; x = x.next) {
                if (x.key == key) {
                    return x.value;
                }
            }
            return -1;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int idx = hash(key);
            Node pre = new Node(-1, -1, nodes[idx]); // sentinal node before list head
            for (Node prev = pre; prev.next != null; prev = prev.next) {
                if (prev.next.key == key) {
                    prev.next = prev.next.next;
                    break;
                }
            }
            nodes[idx] = pre.next;
            size--;
        }

        private int hash(int key) {
            return key % nodes.length;
        }

        private void rehash() {
            Node[] tmp = nodes;
            nodes = new Node[tmp.length * 2];
            size = 0;
            for (Node head: tmp) {
                for (Node x = head; x != null; x = x.next) {
                    put(x.key, x.value);
                }
            }
        }

        class Node {
            int key;
            int value;
            Node next;

            public Node(int key, int value, Node next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }
    }

    class MyHashMapV0 {
        /**
         * Runtime: 90 ms, faster than 13.23% of Java online submissions for Design HashMap.
         * Memory Usage: 57.8 MB, less than 33.75% of Java online submissions for Design HashMap.
         *
         * https://leetcode.com/problems/design-hashmap/discuss/297503/Simple-Java-solution-with-one-int-and-one-boolean
         */

        //Initialize is false
        boolean[] isExist = new boolean[1000001];
        //Initialize a int array
        int[] intArray = new int[1000001];

        /** Initialize your data structure here. */
        public MyHashMapV0() {

        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            intArray[key] = value;
            isExist[key] = true;
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            if(isExist[key] == true){
                return intArray[key];
            }
            return -1;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            intArray[key] = -1;
            isExist[key] = false;
        }
    }
}
