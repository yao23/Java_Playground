package com.leetcode.www; /**
 * Created by liyao on 6/10/17.
 */
import java.util.Arrays;

public class ThreeSumClosest { // LC 16
    /**
     * Runtime: 4 ms, faster than 98.05% of Java online submissions for 3Sum Closest.
     * Memory Usage: 35.4 MB, less than 99.75% of Java online submissions for 3Sum Closest.
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int result = 0;
        int minDiff = Integer.MAX_VALUE;
        int len = nums.length;
        if (len < 3) {
            return result;
        }

        for (int i = 0; i < len - 2; i++) {
            while (i > 0 && i < len - 2 && nums[i - 1] == nums[i]) { // skip duplicate element
                i++;
            }

            int j = i + 1, k = len - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) { // find the target
                    return target;
                } else { // have difference with target
                    int diff = Math.abs(sum - target);
                    if (diff < minDiff) {
                        result = sum;
                        minDiff = diff;
                    }

                    if (sum < target) {
                        j++;
                    } else {
                        k--;
                    }
                }
            }
        }

        return result;
    }

    // [0,0,0], 1 => 0
    // [-1,2,1,-4], 1 => 2 (-1 + 2 + 1)
    // [1,1,1,1], 0 => 3
    // [0,1,2], 3 => 3

    // beats 73.41%
}
