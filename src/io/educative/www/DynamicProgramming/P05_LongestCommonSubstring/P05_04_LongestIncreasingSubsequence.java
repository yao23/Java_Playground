package io.educative.www.DynamicProgramming.P05_LongestCommonSubstring;

class P05_04_LongestIncreasingSubsequence {

    public int findLISLength(int[] nums) {
        return findLISLengthRecursive(nums, 0, -1);
    }

    /**
     * The time complexity of the algorithm is exponential O(2^n), where ‘n’ is the lengths of the input array.
     * The space complexity is O(n) which is used to store the recursion stack.
     *
     * @param nums
     * @param currentIndex
     * @param previousIndex
     * @return
     */
    private int findLISLengthRecursive(int[] nums, int currentIndex, int previousIndex) {
        if(currentIndex == nums.length)
            return 0;

        // include nums[currentIndex] if it is larger than the last included number
        int c1 = 0;
        if(previousIndex == -1 || nums[currentIndex] > nums[previousIndex])
            c1 = 1 + findLISLengthRecursive(nums, currentIndex+1, currentIndex);

        // excluding the number at currentIndex
        int c2 = findLISLengthRecursive(nums, currentIndex+1, previousIndex);

        return Math.max(c1, c2);
    }

    /**
     * Top-down Dynamic Programming with Memoization
     *
     * @param nums
     * @return
     */
    public int findLISLengthV1(int[] nums) {
        Integer[][] dp = new Integer[nums.length][nums.length+1];
        return findLISLengthRecursiveV1(dp, nums, 0, -1);
    }

    /**
     * Since our memoization array dp[nums.length()][nums.length()] stores the results for all the subproblems, we can
     * conclude that we will not have more than N*N subproblems (where ‘N’ is the length of the input sequence).
     * This means that our time complexity will be O(N^2).
     *
     * The above algorithm will be using O(N^2) space for the memoization array. Other than that we will use O(N) space
     * for the recursion call-stack. So the total space complexity will be O(N^2 + N), which is asymptotically
     * equivalent to O(N^2).
     *
     * @param dp
     * @param nums
     * @param currentIndex
     * @param previousIndex
     * @return
     */
    private int findLISLengthRecursiveV1(Integer[][] dp, int[] nums, int currentIndex, int previousIndex) {
        if(currentIndex == nums.length)
            return 0;

        if(dp[currentIndex][previousIndex+1] == null) {
            // include nums[currentIndex] if it is larger than the last included number
            int c1 = 0;
            if(previousIndex == -1 || nums[currentIndex] > nums[previousIndex])
                c1 = 1 + findLISLengthRecursiveV1(dp, nums, currentIndex+1, currentIndex);

            int c2 = findLISLengthRecursiveV1(dp, nums, currentIndex+1, previousIndex);
            dp[currentIndex][previousIndex+1] = Math.max(c1, c2);
        }

        return dp[currentIndex][previousIndex+1];
    }

    /**
     * Bottom-up Dynamic Programming
     *
     * The time complexity of the above algorithm is O(n^2) and the space complexity is O(n).
     * @param nums
     * @return
     */
    public int findLISLength(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;

        int maxLength = 1;
        for (int i=1; i<nums.length; i++) {
            dp[i] = 1;
            for (int j=0; j<i; j++)
                if (nums[i] > nums[j] && dp[i] <= dp[j] ) {
                    dp[i] = dp[j]+1;
                    maxLength = Math.max(maxLength, dp[i]);
                }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        P05_04_LongestIncreasingSubsequence lis = new P05_04_LongestIncreasingSubsequence();
        int[] nums = {4,2,3,6,10,1,12};
        System.out.println(lis.findLISLength(nums));
        nums = new int[]{-4,10,3,7,15};
        System.out.println(lis.findLISLength(nums));
    }
}