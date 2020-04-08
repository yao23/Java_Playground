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

    /**
     * Runtime: 5 ms, faster than 44.27% of Java online submissions for Partition to K Equal Sum Subsets.
     * Memory Usage: 39.7 MB, less than 9.30% of Java online submissions for Partition to K Equal Sum Subsets.
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsetsV1(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;

        Result[] memo = new Result[1 << nums.length];
        memo[(1 << nums.length) - 1] = Result.TRUE;
        return search(0, sum, memo, nums, sum / k);
    }

    private boolean search(int used, int todo, Result[] memo, int[] nums, int target) {
        if (memo[used] == null) {
            memo[used] = Result.FALSE;
            int targ = (todo - 1) % target + 1;
            for (int i = 0; i < nums.length; i++) {
                if ((((used >> i) & 1) == 0) && nums[i] <= targ) {
                    if (search(used | (1<<i), todo - nums[i], memo, nums, target)) {
                        memo[used] = Result.TRUE;
                        break;
                    }
                }
            }
        }
        return memo[used] == Result.TRUE;
    }

    enum Result { TRUE, FALSE }

    /**
     * Runtime: 27 ms, faster than 22.28% of Java online submissions for Partition to K Equal Sum Subsets.
     * Memory Usage: 39.4 MB, less than 9.30% of Java online submissions for Partition to K Equal Sum Subsets.
     *
     * Time Complexity: O(N * 2^N), where NN is the length of nums. There are 2^N states of used (or state in our
     * bottom-up variant), and each state performs O(N) work searching through nums.
     *
     * Space Complexity: O(2^N), the space used by memo (or dp, total in our bottom-up variant).
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsetsV2(int[] nums, int k) {
        int N = nums.length;
        Arrays.sort(nums);
        int sum = Arrays.stream(nums).sum();
        int target = sum / k;
        if (sum % k > 0 || nums[N - 1] > target) return false;

        boolean[] dp = new boolean[1 << N];
        dp[0] = true;
        int[] total = new int[1 << N];

        for (int state = 0; state < (1 << N); state++) {
            if (!dp[state]) continue;
            for (int i = 0; i < N; i++) {
                int future = state | (1 << i);
                if (state != future && !dp[future]) {
                    if (nums[i] <= target - (total[state] % target)) {
                        dp[future] = true;
                        total[future] = total[state] + nums[i];
                    } else {
                        break;
                    }
                }
            }
        }
        return dp[(1 << N) - 1];
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