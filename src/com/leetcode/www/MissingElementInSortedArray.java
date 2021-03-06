public class MissingElementInSortedArray { // 1060 (Facebook)
    /**
     * Runtime: 1 ms, faster than 41.21% of Java online submissions for Missing Element in Sorted Array.
     * Memory Usage: 46.3 MB, less than 100.00% of Java online submissions for Missing Element in Sorted Array.
     *
     * @param nums
     * @param k
     * @return
     */
    public int missingElement(int[] nums, int k) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            int diff = nums[i] - nums[i - 1] - 1;
            if (diff >= k) {
                return nums[i - 1] + k;
            } else {
                k -= diff;
            }
        }
        return nums[len - 1] + k;
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Missing Element in Sorted Array.
     * Memory Usage: 46.6 MB, less than 100.00% of Java online submissions for Missing Element in Sorted Array.
     *
     * Time complexity: O(logN) since it's a binary search algorithm in the worst case when the missing number is
     * less than the last element of the array.
     *
     * Space complexity : O(1) since it's a constant space solution.
     *
     * @param nums
     * @param k
     * @return
     */
    public int missingElementV1(int[] nums, int k) {
        int n = nums.length;
        // If kth missing number is larger than
        // the last element of the array
        if (k > missing(n - 1, nums))
            return nums[n - 1] + k - missing(n - 1, nums);

        int left = 0, right = n - 1, mid;
        // find left = right index such that
        // missing(left - 1) < k <= missing(left)
        while (left != right) {
            mid = left + (right - left) / 2;

            if (missing(mid, nums) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // kth missing number is greater than nums[idx - 1]
        // and less than nums[idx]
        return nums[left - 1] + k - missing(left - 1, nums);
    }

    // Return how many numbers are missing until nums[idx]
    private int missing(int idx, int[] nums) {
        return nums[idx] - nums[0] - idx;
    }
}

// Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.
//
// Example 1: Input: A = [4,7,9,10], K = 1 Output: 5 Explanation: The first missing number is 5.
//
// Example 2: Input: A = [4,7,9,10], K = 3 Output: 8 Explanation: The missing numbers are [5,6,8,…],
// hence the third missing number is 8. Example 3:
//
// Input: A = [1,2,4], K = 3 Output: 6 Explanation: The missing numbers are [3,5,6,7,…],
// hence the third missing number is 6.
//
// Note: 1 <= A.length <= 50000 1 <= A[i] <= 1e7 1 <= K <= 1e8