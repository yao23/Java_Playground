import java.util.Arrays;

public class PartitionToKEqualSumSubsets {
    /**
     * Runtime: 2 ms, faster than 63.90% of Java online submissions for Partition to K Equal Sum Subsets.
     * Memory Usage: 37.1 MB, less than 9.30% of Java online submissions for Partition to K Equal Sum Subsets.
     *
     * Time Complexity: O(k^{N-k} k!), where N is the length of nums, and k is as given. As we skip additional zeroes in
     * groups, naively we will make O(k!) calls to search, then an additional O(k^{N-k}) calls after every element of
     * groups is nonzero.
     *
     * Space Complexity: O(N), the space used by recursive calls to search in our call stack.
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;
        int target = sum / k;

        Arrays.sort(nums);
        int row = nums.length - 1;
        if (nums[row] > target) return false;
        while (row >= 0 && nums[row] == target) {
            row--;
            k--;
        }
        return search(new int[k], row, nums, target);
    }

    private boolean search(int[] groups, int row, int[] nums, int target) {
        if (row < 0) return true;
        int v = nums[row--];
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] + v <= target) {
                groups[i] += v;
                if (search(groups, row, nums, target)) return true;
                groups[i] -= v;
            }
            if (groups[i] == 0) break;
        }
        return false;
    }
}

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k
 * non-empty subsets whose sums are all equal.
 *
 * Example 1:
 *
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 *
 *
 * Note:
 *
 * 1 <= k <= len(nums) <= 16.
 * 0 < nums[i] < 10000.
 */