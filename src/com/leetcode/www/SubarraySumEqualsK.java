package com.leetcode.www;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK { // LC 560
    /**
     * Runtime: 15 ms, faster than 69.95% of Java online submissions for Subarray Sum Equals K.
     * Memory Usage: 38.3 MB, less than 98.95% of Java online submissions for Subarray Sum Equals K.
     *
     * https://leetcode.com/problems/subarray-sum-equals-k/discuss/134689/Three-Approaches-With-Explanation
     *
     * Remember the frequency of all prefix sums. O(n) time and O(n) memory.
     * sum to keep track of sum of all the elements so far. If we can find a prefix sum in the map for sum-k,
     * it means we can form sum == k using the elements after the element corresponding to that prefix sum till
     * the current element (included). Count all such sums at each element.
     *
     * There is a special case like in the Solution V1 when nums[x] == k that is current sum itself is equal to target
     * without any subtractions. For this solution, we can either increment count by 1 whenever sum == k below or
     * make an entry as a special case in our map, preSumFreq.put(0, 1) to cover those cases.
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        preSumFreq.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            count += preSumFreq.getOrDefault(sum - k, 0);
            preSumFreq.put(sum, preSumFreq.getOrDefault(sum,0) + 1);
        }
        return count;
    }

    /**
     * Runtime: 120 ms, faster than 26.06% of Java online submissions for Subarray Sum Equals K.
     * Memory Usage: 38.1 MB, less than 99.01% of Java online submissions for Subarray Sum Equals K.
     *
     * use of prefix sum for the third for-loop in V0. O(n^2)
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySumV1(int[] nums, int k) {
        int count = 0;
        for (int x = 1; x < nums.length; x++) {
            nums[x] += nums[x - 1];
        }
        for (int x = 0; x < nums.length; x++) {
            if (nums[x] == k) {
                ++count;
            }
            for (int y = x + 1; y < nums.length; y++) {
                if (nums[y] - nums[x] == k) {
                    ++count;
                }
            }
        }
        return count;
    }

    /**
     * TLE (Time Limit Exceed)
     * brute-force approach - for all sum[i,j] count how many times we saw k. O(n^3)
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySumV0(int[] nums, int k) {
        int count = 0;
        for (int x = 0; x < nums.length; x++) {
            for (int y = x; y < nums.length; y++) {
                int sum = 0;
                for (int z = x; z <= y; z++)
                    sum += nums[z];
                if (sum == k) {
                    ++count;
                }
            }
        }
        return count;
    }
}
