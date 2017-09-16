package com.leetcode.www;

import java.util.HashSet;
import java.util.Set;

public class MaximumXOROfTwoNumbersInAnArray { // LC 421
    public int findMaximumXOR(int[] nums) { // beats 81.20%
        int max = 0, mask = 0;
        for (int i = 31; i >= 0; i--) {
            mask = mask | (1 << i); // prefix mask
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num & mask);
            }
            int tmp = max | (1 << i);
            for (int prefix : set) {
                if (set.contains(tmp ^ prefix)) { // prefix contribute to max XOR
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }

    public int findMaximumXORV1(int[] nums) { // beats 89.33%
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // Init Trie.
        Object[] root = {null, null};
        for (int num: nums) {
            Object[] curNode = root;
            for (int i = 31; i >= 0; i --) {
                int curBit = (num >>> i) & 1;
                if (curNode[curBit] == null) {
                    curNode[curBit] = new Object[]{null, null};
                }
                curNode = (Object[]) curNode[curBit];
            }
        }
        int max = Integer.MIN_VALUE;
        for (int num: nums) {
            Object[] curNode = root;
            int curSum = 0;
            for (int i = 31; i >= 0; i--) {
                int curBit = (num >>> i) & 1;
                if (curNode[curBit ^ 1] != null) { // opposite bit (curBit ^ 1)
                    curSum += (1 << i);
                    curNode = (Object[]) curNode[curBit ^ 1];
                } else {
                    curNode = (Object[]) curNode[curBit];
                }
            }
            max = Math.max(curSum, max);
        }
        return max;
    }
}

// [3, 10, 5, 25, 2, 8] => 28 (5 ^ 25)

