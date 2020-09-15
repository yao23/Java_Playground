public class SplitArrayLargestSum { // 410 [Google]
    class Solution {
        /**
         * DP
         *
         * Time complexity : O(n^2 * m). The total number of states is O(n * m). To compute each state f[i][j],
         * we need to go through the whole array to find the optimum k. This requires another O(n) loop.
         * So the total time complexity is O(n ^ 2 * m).
         *
         * Space complexity : O(n * m). The space complexity is equivalent to the number of states, which is O(n * m).
         *
         * @param nums
         * @param m
         * @return
         */
        public int splitArray(int[] nums, int m) {
            int n = nums.length;
            int[][] f = new int[n + 1][m + 1];
            int[] sub = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= m; j++) {
                    f[i][j] = Integer.MAX_VALUE;
                }
            }
            for (int i = 0; i < n; i++) {
                sub[i + 1] = sub[i] + nums[i];
            }
            f[0][0] = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    for (int k = 0; k < i; k++) {
                        f[i][j] = Math.min(f[i][j], Math.max(f[k][j - 1], sub[i] - sub[k]));
                    }
                }
            }
            return f[n][m];
        }
    }

    /**
     * Binary Search + Greedy
     *
     * Time complexity : O(n * log(sum of array)). The binary search costs O(log(sum of array)), where sum of array is
     * the sum of elements in nums. For each computation of F(x), the time complexity is O(n) since we only need to go
     * through the whole array.
     *
     * Space complexity : O(n). Same as the Brute Force approach. We only need the space to store the array.
     *
     * @param nums
     * @param m
     * @return
     */
    public int splitArray(int[] nums, int m) {
        long l = 0;
        long r = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            r += nums[i];
            if (l < nums[i]) {
                l = nums[i];
            }
        }
        long ans = r;
        while (l <= r) {
            long mid = (l + r) >> 1;
            long sum = 0;
            int cnt = 1;
            for (int i = 0; i < n; i++) {
                if (sum + nums[i] > mid) {
                    cnt ++;
                    sum = nums[i];
                } else {
                    sum += nums[i];
                }
            }
            if (cnt <= m) {
                ans = Math.min(ans, mid);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int)ans;
    }


    private int ans;
    private int n, m;
    private void dfs(int[] nums, int i, int cntSubarrays, int curSum, int curMax) {
        if (i == n && cntSubarrays == m) {
            ans = Math.min(ans, curMax);
            return;
        }
        if (i == n) {
            return;
        }
        if (i > 0) {
            dfs(nums, i + 1, cntSubarrays, curSum + nums[i], Math.max(curMax, curSum + nums[i]));
        }
        if (cntSubarrays < m) {
            dfs(nums, i + 1, cntSubarrays + 1, nums[i], Math.max(curMax, nums[i]));
        }
    }

    /**
     * Brute Force [Time Limit Exceeded - TLE]
     *
     * Time complexity : O(n^m). To split n elements into m parts, we can have
     * (m−1)
     * (n−1)
     * ​ different solutions. This is equivalent to n ^ m.
     *
     * Space complexity : O(n). We only need the space to store the array.
     *
     * @param nums
     * @param M
     * @return
     */
    public int splitArrayV0(int[] nums, int M) {
        ans = Integer.MAX_VALUE;
        n = nums.length;
        m = M;
        dfs(nums, 0, 0, 0, 0);
        return ans;
    }
}
