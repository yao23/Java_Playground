package com.leetcode.www;

import java.util.HashMap;
import java.util.Map;

public class TwoSumIII { // LC 170
    /**
     * Your TwoSum object will be instantiated and called as such:
     * TwoSum obj = new TwoSum();
     * obj.add(number);
     * boolean param_2 = obj.find(value);
     */

    class TwoSum {
        private Map<Integer, Integer> map;

        /** Initialize your data structure here. */
        public TwoSum() {
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
                        return map.get(num) > 1;
                    } else {
                        return true;
                    }
                } else {
                    return false;
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
