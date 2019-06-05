package com.leetcode.www;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

    /**
     * Your TimeMap object will be instantiated and called as such:
     * TimeMap obj = new TimeMap();
     * obj.set(key,value,timestamp);
     * String param_2 = obj.get(key,timestamp);
     */
}
