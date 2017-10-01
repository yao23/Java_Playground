package com.leetcode.www;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSumIII { // LC 170
    /**
     * Your TwoSum object will be instantiated and called as such:
     * TwoSum obj = new TwoSum();
     * obj.add(number);
     * boolean param_2 = obj.find(value);
     */

    class TwoSum { // beats 80.32%
        private List<Integer> list = new ArrayList<>();
        private Map<Integer, Integer> map = new HashMap<>();

        /** Initialize your data structure here. */
        public TwoSum() {
            map = new HashMap<>();
        }

        // Add the number to an internal data structure.
        public void add(int number) {
            if (map.containsKey(number)) {
                map.put(number, map.get(number) + 1);
            } else {
                map.put(number, 1);
                list.add(number);
            }
        }

        // Find if there exists any pair of numbers which sum is equal to the value.
        public boolean find(int value) {
            for (int i = 0; i < list.size(); i++) { // faster than iterating hashmap
                int num1 = list.get(i), num2 = value - num1;
                if ((num1 == num2 && map.get(num1) > 1) || (num1 != num2 && map.containsKey(num2))) {
                    return true;
                }
            }
            return false;
        }
    }

    class TwoSumV0 { // beats 12.55%
        private Map<Integer, Integer> map;

        /** Initialize your data structure here. */
        public TwoSumV0() {
            map = new HashMap<>();
        }

        /** Add the number to an internal data structure.. */
        public void add(int number) {
            int curNum;
            if (map.containsKey(number)) {
                curNum = map.get(number) + 1;
            } else {
                curNum = 0;
            }
            map.put(number, curNum + 1);
        }

        /** Find if there exists any pair of numbers which sum is equal to the value. */
        public boolean find(int value) {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int num = entry.getKey();
                int diff = value - num;
                if (map.containsKey(diff)) {
                    if (diff == num) {
                        if (map.get(num) > 1) {
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}

// ["TwoSum","add","add","add","find","find"]
// [[],[1],[3],[5],[4],[7]]
// =>
// [null,null,null,null,true,false]

// ["TwoSum","add","add","add","find"]
// [[],[0],[-1],[1],[0]]
// =>
// [null,null,null,null,true]
