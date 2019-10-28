package io.educative.www.DynamicProgramming.P05_LongestCommonSubstring;

class P05_07_MinimumDeletionsToMakeASequenceSorted {

    public int findMinimumDeletions(int[] nums){
        // subtracting the length of LIS from the length of the input array to get minimum number of deletions
        return nums.length - findLISLength(nums);
    }

    /**
     * Bottom-up Dynamic Programming
     *
     * The time complexity of the algorithm is O(n^2) and the space complexity is O(n).
     *
     * @param nums
     * @return
     */
    private int findLISLength(int[] nums) {
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
        P05_07_MinimumDeletionsToMakeASequenceSorted mdss = new P05_07_MinimumDeletionsToMakeASequenceSorted();
        int[] nums = {4,2,3,6,10,1,12};
        System.out.println(mdss.findMinimumDeletions(nums));
        nums = new int[]{-4,10,3,7,15};
        System.out.println(mdss.findMinimumDeletions(nums));
        nums = new int[]{3,2,1,0};
        System.out.println(mdss.findMinimumDeletions(nums));
    }
}
