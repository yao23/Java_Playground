package org.geeksforgeeks;

public class MinimumSubsetSumDifference {
    // Function to find the minimum sum
    private static int findAction(int arr[], int i, int sumCalculated, int sumTotal) {
        // If we have reached last element. Sum of one subset is sumCalculated, sum of other subset is sumTotal -
        // sumCalculated.  Return absolute difference of two sums.
        if (i == 0) {
            return Math.abs((sumTotal - sumCalculated) - sumCalculated);
        }

        // For every item arr[i], we have two choices: (1) We do not include it first set, (2) We include it in first set
        // We return minimum of two choices
        return Math.min(findAction(arr, i - 1, sumCalculated + arr[i - 1], sumTotal),
                findAction(arr, i - 1, sumCalculated, sumTotal));
    }

    /**
     * https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/
     *
     * Returns minimum possible difference between sums of two subsets
     *
     * @param arr
     * @param n
     * @return
     */
    private static int findMinSubsetSumDifference(int arr[], int n) {
        // Compute total sum of elements
        int sumTotal = 0;
        for (int i = 0; i < n; i++) {
            sumTotal += arr[i];
        }

        // Compute result using recursive function
        return findAction(arr, n, 0, sumTotal);
    }

    /**
     * Returns the minimum value of the difference of the two sets.
     *
     * Time Complexity = O(n*sum) where n is number of elements and sum is sum of all elements.
     * Note that the above solution is in Pseudo Polynomial Time (time complexity is dependent on numeric value of input).
     *
     * @param arr
     * @param n
     * @return
     */
    static int findMin(int arr[], int n) {
        // Calculate sum of all elements
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        // Create an array to store results of subproblems
        boolean dp[][] = new boolean[n + 1][sum + 1];

        // Initialize first column as true. 0 sum is possible with all elements.
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Initialize top row, except dp[0][0], as false. With 0 elements, no other sum except 0 is possible
        for (int i = 1; i <= sum; i++) {
            dp[0][i] = false;
        }

        // Fill the partition table in bottom up manner
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                // If i'th element is excluded
                dp[i][j] = dp[i - 1][j];

                // If i'th element is included
                if (arr[i - 1] <= j) {
                    dp[i][j] |= dp[i - 1][j - arr[i - 1]];
                }
            }
        }

        // Initialize difference of two sums.
        int diff = Integer.MAX_VALUE;

        // Find the largest j such that dp[n][j] is true where j loops from sum/2 t0 0
        for (int j = sum / 2; j >= 0; j--) {
            // Find the
            if (dp[n][j] == true) {
                diff = sum - 2 * j;
                break;
            }
        }
        return diff;
    }

    /* test above function */
    public static void main(String[] args) {
        int arr[] = {3, 1, 4, 2, 2, 1};
        int n = arr.length;
        System.out.print("The minimum difference between two sets is " + findMinSubsetSumDifference(arr, n));
    }
}
