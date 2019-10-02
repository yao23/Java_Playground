package io.educative.www.DynamicProgramming.P01_ZeroOneKnapsack;

public class P01_04_MinimumSubsetSumDifference {
    /**
     * Time and Space complexity
     * Because of the two recursive calls, the time complexity of the above algorithm is exponential O(2^n), where ‘n’
     * represents the total number. The space complexity is O(n) which is used to store the recursion stack.
     *
     * @param num
     * @return
     */
    public int canPartition(int[] num) {
        return this.canPartitionRecursive(num, 0, 0, 0);
    }

    private int canPartitionRecursive(int[] num, int currentIndex, int sum1, int sum2) {
        // base check
        if (currentIndex == num.length)
            return Math.abs(sum1 - sum2);

        // recursive call after including the number at the currentIndex in the first set
        int diff1 = canPartitionRecursive(num, currentIndex+1, sum1+num[currentIndex], sum2);

        // recursive call after including the number at the currentIndex in the second set
        int diff2 = canPartitionRecursive(num, currentIndex+1, sum1, sum2+num[currentIndex]);

        return Math.min(diff1, diff2);
    }

    /**
     * Top-down Dynamic Programming with Memoization
     *
     * @param num
     * @return
     */
    public int canPartitionV1(int[] num) {
        int sum = 0;
        for (int i = 0; i < num.length; i++)
            sum += num[i];

        Integer[][] dp = new Integer[num.length][sum + 1];
        return this.canPartitionRecursive(dp, num, 0, 0, 0);
    }

    private int canPartitionRecursive(Integer[][] dp, int[] num, int currentIndex, int sum1, int sum2) {
        // base check
        if(currentIndex == num.length)
            return Math.abs(sum1 - sum2);

        // check if we have not already processed similar problem
        if(dp[currentIndex][sum1] == null) {
            // recursive call after including the number at the currentIndex in the first set
            int diff1 = canPartitionRecursive(dp, num, currentIndex + 1, sum1 + num[currentIndex], sum2);

            // recursive call after including the number at the currentIndex in the second set
            int diff2 = canPartitionRecursive(dp, num, currentIndex + 1, sum1, sum2 + num[currentIndex]);

            dp[currentIndex][sum1] = Math.min(diff1, diff2);
        }

        return dp[currentIndex][sum1];
    }

    /**
     * Bottom-up Dynamic Programming
     *
     * Time and Space complexity
     * The above solution has the time and space complexity of O(N*S), where ‘N’ represents total numbers and ‘S’ is the
     * total sum of all the numbers.
     *
     * @param num
     * @return
     */
    public int canPartitionV2(int[] num) {
        int sum = 0;
        for (int i = 0; i < num.length; i++)
            sum += num[i];

        int n = num.length;
        boolean[][] dp = new boolean[n][sum/2 + 1];

        // populate the sum=0 columns, as we can always form '0' sum with an empty set
        for(int i=0; i < n; i++)
            dp[i][0] = true;

        // with only one number, we can form a subset only when the required sum is equal to that number
        for(int s=1; s <= sum/2 ; s++) {
            dp[0][s] = (num[0] == s ? true : false);
        }

        // process all subsets for all sums
        for(int i=1; i < num.length; i++) {
            for(int s=1; s <= sum/2; s++) {
                // if we can get the sum 's' without the number at index 'i'
                if(dp[i-1][s]) {
                    dp[i][s] = dp[i-1][s];
                } else if (s >= num[i]) {
                    // else include the number and see if we can find a subset to get the remaining sum
                    dp[i][s] = dp[i-1][s-num[i]];
                }
            }
        }

        int sum1 = 0;
        // Find the largest index in the last row which is true
        for (int i = sum / 2; i >= 0; i--) {
            if (dp[n-1][i] == true) {
                sum1 = i;
                break;
            }
        }

        int sum2 = sum - sum1;
        return Math.abs(sum2-sum1);
    }

    public static void main(String[] args) {
        P01_04_MinimumSubsetSumDifference ps = new P01_04_MinimumSubsetSumDifference();
        int[] num = {1, 2, 3, 9};
        System.out.println(ps.canPartition(num));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(ps.canPartition(num));
        num = new int[]{1, 3, 100, 4};
        System.out.println(ps.canPartition(num));
    }
}
