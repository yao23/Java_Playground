package com.leetcode.www;

import java.util.*;

public class TimeBasedKeyValueStore { // LC 981
    /**
     * Runtime: 243 ms, faster than 48.89% of Java online submissions for Time Based Key-Value Store.
     * Memory Usage: 136 MB, less than 96.66% of Java online submissions for Time Based Key-Value Store.
     *
     * https://leetcode.com/problems/time-based-key-value-store/discuss/306078/TreeMap-Approach
     */
    class TimeMap {

        Map<String, TreeMap<Integer, String>> map = new HashMap<>();

        /** Initialize your data structure here. */
        public TimeMap() {

        }

        public void set(String key, String value, int timestamp) {
            TreeMap<Integer, String> innerMap;

            if (map.containsKey(key)) {
                innerMap = map.get(key);
                innerMap.put(timestamp, value);
                return;
            }
            innerMap = new TreeMap<>(Collections.reverseOrder());
            innerMap.put(timestamp, value);

            map.put(key, innerMap);
        }

        public String get(String key, int timestamp) {
            TreeMap<Integer, String> innerMap;

            if (map.containsKey(key)) {
                innerMap = map.get(key);

                for (Map.Entry<Integer, String> entry : innerMap.entrySet()) {
                    //System.out.println(entry.getKey() + "/" + entry.getValue());

                    int key2 = entry.getKey();

                    if (key2<=timestamp) {
                        return entry.getValue();
                    }
                }

            }

            return "";
        }
    }

    class TimeMapV0 {

        // Stores key to Node map
        // where Node stores list of timestamp and list of values both indexed same
        // since timestamps is strictly increasing, we can do binary search on it
        Map<String, Node> map;

        /** Initialize your data structure here. */
        public TimeMapV0() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            Node node = map.getOrDefault(key, new Node());
            node.add(timestamp, value);
            map.put(key, node);
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) {
                return "";
            }
            Node node = map.get(key);
            return search(timestamp, node);
        }

        private String search(int timestamp, Node node) {
            int i = 0, j = node.times.size() - 1;
            // some base cases
            if (timestamp < node.times.get(0)) {
                return "";
            }
            if (timestamp >= node.times.get(j)) {
                return node.values.get(j);
            }
            while (i < j) {
                int m = (i + j) / 2;
                if (timestamp == node.times.get(m)) {
                    return node.values.get(m);
                } else if (timestamp > node.times.get(m)) {
                    i = m + 1;
                } else {
                    j = m;
                }
            }
            return i == 0 ? "" : node.values.get(i - 1);
        }

        class Node {
            List<Integer> times;
            List<String> values;
            Node() {
                times = new ArrayList<>();
                values = new ArrayList<>();
            }

            public void add(int time, String value) {
                times.add(time);
                values.add(value);
            }
        }
    }


    /**
     * Your TimeMap object will be instantiated and called as such:
     * TimeMap obj = new TimeMap();
     * obj.set(key,value,timestamp);
     * String param_2 = obj.get(key,timestamp);
     */
}
