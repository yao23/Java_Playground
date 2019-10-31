package io.educative.www.DynamicProgramming.P05_LongestCommonSubstring;

class P05_10_LongestBitonicSubsequence {

    private int findLBSLength(int[] nums) {
        int maxLength = 0;
        for(int i=0; i<nums.length; i++) {
            int c1 = findLDSLength(nums, i, -1);
            int c2 = findLDSLengthRev(nums, i, -1);
            maxLength = Math.max(maxLength, c1+c2-1);
        }
        return maxLength;
    }

    /**
     * The time complexity of the algorithm is exponential O(2^n), where ‘n’ is the lengths of the input array. The
     * space complexity is O(n) which is used to store the recursion stack.
     *
     * @param nums
     * @param currentIndex
     * @param previousIndex
     * @return
     */
    // find the longest decreasing subsequence from currentIndex till the end of the array
    private int findLDSLength(int[] nums, int currentIndex, int previousIndex) {
        if(currentIndex == nums.length)
            return 0;

        // include nums[currentIndex] if it is smaller than the previous number
        int c1 = 0;
        if(previousIndex == -1 || nums[currentIndex] < nums[previousIndex])
            c1 = 1 + findLDSLength(nums, currentIndex+1, currentIndex);

        // excluding the number at currentIndex
        int c2 = findLDSLength(nums, currentIndex+1, previousIndex);

        return Math.max(c1, c2);
    }

    // find the longest decreasing subsequence from currentIndex till the beginning of the array
    private int findLDSLengthRev(int[] nums, int currentIndex, int previousIndex) {
        if(currentIndex < 0)
            return 0;

        // include nums[currentIndex] if it is smaller than the previous number
        int c1 = 0;
        if(previousIndex == -1 || nums[currentIndex] < nums[previousIndex])
            c1 = 1 + findLDSLengthRev(nums, currentIndex-1, currentIndex);

        // excluding the number at currentIndex
        int c2 = findLDSLengthRev(nums, currentIndex-1, previousIndex);

        return Math.max(c1, c2);
    }

    /**
     * Top-down Dynamic Programming with Memoization
     *
     * @param nums
     * @return
     */
    private int findLBSLengthV1(int[] nums) {
        Integer[][] lds = new Integer[nums.length][nums.length+1];
        Integer[][] ldsRev = new Integer[nums.length][nums.length+1];

        int maxLength = 0;
        for(int i=0; i<nums.length; i++) {
            int c1 = findLDSLengthV1(lds, nums, i, -1);
            int c2 = findLDSLengthReverseV1(ldsRev, nums, i, -1);
            maxLength = Math.max(maxLength, c1+c2-1);
        }

        return maxLength;
    }

    // find the longest decreasing subsequence from currentIndex till the end of the array
    private int findLDSLengthV1(Integer[][] dp, int[] nums, int currentIndex, int previousIndex) {
        if(currentIndex == nums.length)
            return 0;

        if(dp[currentIndex][previousIndex+1] == null) {
            // include nums[currentIndex] if it is smaller than the previous number
            int c1 = 0;
            if(previousIndex == -1 || nums[currentIndex] < nums[previousIndex])
                c1 = 1 + findLDSLengthV1(dp, nums, currentIndex+1, currentIndex);

            // excluding the number at currentIndex
            int c2 = findLDSLengthV1(dp, nums, currentIndex+1, previousIndex);

            dp[currentIndex][previousIndex+1] = Math.max(c1, c2);
        }

        return dp[currentIndex][previousIndex+1];
    }

    // find the longest decreasing subsequence from currentIndex till the beginning of the array
    private int findLDSLengthReverseV1(Integer[][] dp, int[] nums, int currentIndex, int previousIndex) {
        if(currentIndex < 0)
            return 0;

        if(dp[currentIndex][previousIndex+1] == null) {
            // include nums[currentIndex] if it is smaller than the previous number
            int c1 = 0;
            if(previousIndex == -1 || nums[currentIndex] < nums[previousIndex])
                c1 = 1 + findLDSLengthReverseV1(dp, nums, currentIndex-1, currentIndex);

            // excluding the number at currentIndex
            int c2 = findLDSLengthReverseV1(dp, nums, currentIndex-1, previousIndex);

            dp[currentIndex][previousIndex+1] = Math.max(c1, c2);
        }
        return dp[currentIndex][previousIndex+1];
    }

    /**
     * Bottom-up Dynamic Programming
     *
     * The time complexity of the above algorithm is O(n^2) and the space complexity is O(n).
     *
     * @param nums
     * @return
     */
    private int findLBSLength(int[] nums) {
        int[] lds = new int[nums.length];
        int[] ldsReverse = new int[nums.length];

        // find LDS for every index up to the beginning of the array
        for (int i = 0; i < nums.length; i++) {
            lds[i] = 1; // every element is an LDS of length 1
            for (int j = i - 1; j >= 0; j--)
                if (nums[j] < nums[i]) {
                    lds[i] = Math.max(lds[i], lds[j] + 1);
                }
        }

        // find LDS for every index up to the end of the array
        for (int i = nums.length - 1; i >= 0; i--) {
            ldsReverse[i] = 1; // every element is an LDS of length 1
            for (int j = i + 1; j < nums.length; j++)
                if (nums[j] < nums[i]) {
                    ldsReverse[i] = Math.max(ldsReverse[i], ldsReverse[j] + 1);
                }
        }

        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            maxLength = Math.max(maxLength, lds[i] + ldsReverse[i] - 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        P05_10_LongestBitonicSubsequence lbs = new P05_10_LongestBitonicSubsequence();
        int[] nums = {4,2,3,6,10,1,12};
        System.out.println(lbs.findLBSLength(nums));
        nums = new int[]{4,2,5,9,7,6,10,3,1};
        System.out.println(lbs.findLBSLength(nums));
    }
}
