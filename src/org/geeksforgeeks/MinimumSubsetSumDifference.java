package org.geeksforgeeks;

public class MinimumSubsetSumDifference {
    // Function to find the minimum sum
    private static int findAction(int arr[], int i, int sumCalculated, int sumTotal) {
        // If we have reached last element. Sum of one subset is sumCalculated, sum of other subset is sumTotal -
        // sumCalculated.  Return absolute difference of two sums.
        if (i == 0) {
            return Math.abs((sumTotal - sumCalculated) - sumCalculated);
        }

        // For every item arr[i], we have two choices
        // (1) We do not include it first set
        // (2) We include it in first set
        // We return minimum of two choices
        return Math.min(findAction(arr, i - 1, sumCalculated + arr[i - 1], sumTotal),
                findAction(arr, i - 1, sumCalculated, sumTotal));
    }

    // Returns minimum possible difference between sums of two subsets
    private static int findMinSubsetSumDifference(int arr[], int n) {
        // Compute total sum of elements
        int sumTotal = 0;
        for (int i = 0; i < n; i++) {
            sumTotal += arr[i];
        }

        // Compute result using recursive function
        return findAction(arr, n, 0, sumTotal);
    }

    /* test above function */
    public static void main(String[] args) {
        int arr[] = {3, 1, 4, 2, 2, 1};
        int n = arr.length;
        System.out.print("The minimum difference between two sets is " + findMinSubsetSumDifference(arr, n));
    }
}
