package io.educative.www.DynamicProgramming.P05_LongestCommonSubstring;

class P05_04_MaximumSumIncreasingSubsequence {

    public int findMSIS(int[] nums) {
        return findMSISRecursive(nums, 0, -1, 0);
    }

    /**
     * The time complexity of the above algorithm is exponential O(2^n), where ‘n’ is the lengths of the input array.
     * The space complexity is O(n) which is used to store the recursion stack.
     *
     * @param nums
     * @param currentIndex
     * @param previousIndex
     * @param sum
     * @return
     */
    private int findMSISRecursive(int[] nums, int currentIndex, int previousIndex, int sum) {
        if(currentIndex == nums.length)
            return sum;

        // include nums[currentIndex] if it is larger than the last included number
        int s1 = sum;
        if(previousIndex == -1 || nums[currentIndex] > nums[previousIndex])
            s1 = findMSISRecursive(nums, currentIndex+1, currentIndex, sum + nums[currentIndex]);

        // excluding the number at currentIndex
        int s2 = findMSISRecursive(nums, currentIndex+1, previousIndex, sum);

        return Math.max(s1, s2);
    }

    /**
     * Top-down Dynamic Programming with Memoization
     *
     * @param nums
     * @return
     */
    public int findMSISV1(int[] nums) {
        Map<String, Integer> dp = new HashMap<>();
        return findMSISRecursiveV1(dp, nums, 0, -1, 0);
    }

    private int findMSISRecursiveV1(Map<String, Integer> dp, int[] nums, int currentIndex, int previousIndex, int sum) {
        if(currentIndex == nums.length)
            return sum;

        String subProblemKey = currentIndex + "-" + previousIndex + "-" + sum;
        if(!dp.containsKey(subProblemKey)) {
            // include nums[currentIndex] if it is larger than the last included number
            int s1 = sum;
            if(previousIndex == -1 || nums[currentIndex] > nums[previousIndex])
                s1 = findMSISRecursiveV1(dp, nums, currentIndex+1, currentIndex, sum + nums[currentIndex]);

            // excluding the number at currentIndex
            int s2 = findMSISRecursiveV1(dp, nums, currentIndex+1, previousIndex, sum);
            dp.put(subProblemKey, Math.max(s1, s2));
        }

        return dp.get(subProblemKey);
    }

    /**
     * Bottom-up Dynamic Programming
     *
     * The time complexity of the above algorithm is O(n^2) and the space complexity is O(n).
     *
     * @param nums
     * @return
     */
    public int findMSIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        int maxSum = nums[0];
        for (int i=1; i<nums.length; i++) {
            dp[i] = nums[i];
            for (int j=0; j<i; j++) {
                if (nums[i] > nums[j] && dp[i] < dp[j] + nums[i])
                    dp[i] = dp[j] + nums[i];
            }
            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        P05_04_MaximumSumIncreasingSubsequence msis = new P05_04_MaximumSumIncreasingSubsequence();
        int[] nums = {4,1,2,6,10,1,12};
        System.out.println(msis.findMSIS(nums));
        nums = new int[]{-4,10,3,7,15};
        System.out.println(msis.findMSIS(nums));
    }
}
