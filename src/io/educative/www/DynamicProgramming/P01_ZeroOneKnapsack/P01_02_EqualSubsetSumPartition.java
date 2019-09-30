package io.educative.www.DynamicProgramming.P01_ZeroOneKnapsack;

public class P01_02_EqualSubsetSumPartition {
    /**
     * Time and Space complexity
     * The time complexity of the above algorithm is exponential O(2^n), where ‘n’ represents the total number.
     * The space complexity is O(n), which will be used to store the recursion stack.
     *
     * @param num
     * @return
     */
    public boolean canPartition(int[] num) {
        int sum = 0;
        for (int i = 0; i < num.length; i++)
            sum += num[i];

        // if 'sum' is a an odd number, we can't have two subsets with equal sum
        if(sum % 2 != 0)
            return false;

        return this.canPartitionRecursive(num, sum/2, 0);
    }

    private boolean canPartitionRecursive(int[] num, int sum, int currentIndex) {
        // base check
        if (sum == 0)
            return true;

        if(num.length == 0 || currentIndex >= num.length)
            return false;

        // recursive call after choosing the number at the currentIndex
        // if the number at currentIndex exceeds the sum, we shouldn't process this
        if( num[currentIndex] <= sum ) {
            if(canPartitionRecursive(num, sum - num[currentIndex], currentIndex + 1))
                return true;
        }

        // recursive call after excluding the number at the currentIndex
        return canPartitionRecursive(num, sum, currentIndex + 1);
    }

    /**
     * Top-down Dynamic Programming with Memoization
     *
     * Time and Space complexity
     * The above algorithm has the time and space complexity of O(N*S), where ‘N’ represents total numbers and ‘S’
     * is the total sum of all the numbers.
     *
     * @param num
     * @return
     */
    public boolean canPartitionV1(int[] num) {
        int sum = 0;
        for (int i = 0; i < num.length; i++)
            sum += num[i];

        // if 'sum' is a an odd number, we can't have two subsets with equal sum
        if (sum % 2 != 0)
            return false;

        Boolean[][] dp = new Boolean[num.length][sum / 2 + 1];
        return this.canPartitionRecursive(dp, num, sum / 2, 0);
    }

    private boolean canPartitionRecursive(Boolean[][] dp, int[] num, int sum, int currentIndex) {
        // base check
        if (sum == 0)
            return true;

        if (num.length == 0 || currentIndex >= num.length)
            return false;

        // if we have not already processed a similar problem
        if (dp[currentIndex][sum] == null) {
            // recursive call after choosing the number at the currentIndex
            // if the number at currentIndex exceeds the sum, we shouldn't process this
            if (num[currentIndex] <= sum) {
                if (canPartitionRecursive(dp, num, sum - num[currentIndex], currentIndex + 1)) {
                    dp[currentIndex][sum] = true;
                    return true;
                }
            }

            // recursive call after excluding the number at the currentIndex
            dp[currentIndex][sum] = canPartitionRecursive(dp, num, sum, currentIndex + 1);
        }

        return dp[currentIndex][sum];
    }

    /**
     * Bottom-up Dynamic Programming
     *
     * This means, dp[i][s] will be ‘true’ if we can make the sum ‘s’ from the first ‘i’ numbers.
     *
     * Time and Space complexity
     * The above solution the has time and space complexity of O(N*S), where ‘N’ represents total numbers and ‘S’ is the
     * total sum of all the numbers.
     *
     * @param num
     * @return
     */
    public boolean canPartitionV2(int[] num) {
        int n = num.length;
        // find the total sum
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += num[i];

        // if 'sum' is a an odd number, we can't have two subsets with same total
        if(sum % 2 != 0)
            return false;

        // we are trying to find a subset of given numbers that has a total sum of ‘sum/2’.
        sum /= 2;

        boolean[][] dp = new boolean[n][sum + 1];

        // populate the sum=0 columns, as we can always for '0' sum with an empty set
        for(int i=0; i < n; i++)
            dp[i][0] = true;

        // with only one number, we can form a subset only when the required sum is equal to its value
        for(int s=1; s <= sum ; s++) {
            dp[0][s] = (num[0] == s ? true : false);
        }

        // process all subsets for all sums
        for(int i=1; i < n; i++) {
            for(int s=1; s <= sum; s++) {
                // if we can get the sum 's' without the number at index 'i'
                if(dp[i-1][s]) {
                    dp[i][s] = dp[i-1][s];
                } else if (s >= num[i]) { // else if we can find a subset to get the remaining sum
                    dp[i][s] = dp[i-1][s-num[i]];
                }
            }
        }

        // the bottom-right corner will have our answer.
        return dp[n-1][sum];
    }

    public static void main(String[] args) {
        P01_02_EqualSubsetSumPartition ps = new P01_02_EqualSubsetSumPartition();
        int[] num = {1, 2, 3, 4};
        System.out.println(ps.canPartition(num));
        num = new int[]{1, 1, 3, 4, 7};
        System.out.println(ps.canPartition(num));
        num = new int[]{2, 3, 4, 6};
        System.out.println(ps.canPartition(num));
    }
}
