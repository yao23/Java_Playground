package com.leetcode.www;

import java.util.*;

public class LargestDivisibleSubset { // LC 368
    public List<Integer> largestDivisibleSubset(int[] nums) { // beats 83.85%
        int n = nums.length;
        int[] count = new int[n]; // max divisible number for current number
        int[] pre = new int[n]; // previous divisible number for each
        Arrays.sort(nums);
        int max = 0, index = -1;
        for (int i = 0; i < n; i++) {
            count[i] = 1;
            pre[i] = -1;
            for (int j = i - 1; j >= 0; j--) { // from end to start, reduce computation num
                if (nums[i] % nums[j] == 0) {
                    if (1 + count[j] > count[i]) {
                        count[i] = count[j] + 1;
                        pre[i] = j;
                    }
                }
            }
            if (count[i] > max) {
                max = count[i];
                index = i;
            }
        }
        List<Integer> res = new ArrayList<>();
        while (index != -1) {
            res.add(nums[index]);
            index = pre[index];
        }
        return res;
    }

    public List<Integer> largestDivisibleSubsetV0(int[] nums) { // beats 6.88%
        List<Integer> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(nums[0], new ArrayList<>()); // init for 1st element
        map.get(nums[0]).add(nums[0]);
        int[] dp = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            int maxIdx = -1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    maxIdx = j;
                }
            }
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new ArrayList<>());
            }
            if (maxIdx >= 0) {
                map.get(nums[i]).addAll(map.get(nums[maxIdx]));
            }
            map.get(nums[i]).add(nums[i]); // add itself into list
        }
        int maxKey = -1;
        int maxSize = 0;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() > maxSize) {
                maxKey = entry.getKey();
                maxSize = entry.getValue().size();
            }
        }
        if (maxKey >= 0) {
            res = new ArrayList<>(map.get(maxKey));
        }

        return res;
    }
}

// nums: [1,2,3] => Result: [1,2] (of course, [1,3] will also be ok)
// nums: [1,2,4,8] => Result: [1,2,4,8]