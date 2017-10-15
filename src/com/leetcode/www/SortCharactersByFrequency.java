package com.leetcode.www;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortCharactersByFrequency { // LC 451
    /**
     * use a map a count and according to the frequency to put it into the right bucket. Then we go through the bucket
     * to get the most frequently character and append that to the final StringBuilder.
     *
     * @param s
     * @return
     */
    public String frequencySort(String s) { // beats 46.71%
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        List<Character>[] bucket = new List[s.length() + 1];
        for (char key : map.keySet()) {
            int frequency = map.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }
        StringBuilder sb = new StringBuilder();
        for (int pos = bucket.length - 1; pos >= 0; pos--) {
            if (bucket[pos] != null) {
                for (char num : bucket[pos]) {
                    for (int i = 0; i < map.get(num); i++) {
                        sb.append(num);
                    }
                }
            }
        }
        return sb.toString();
    }
}
