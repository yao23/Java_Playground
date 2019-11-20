package io.educative.www.Coding.P02_TwoPointers;

import java.util.*;

class P02_05_TripletSumCloseToTarget {
    /**
     * Time complexity
     * Sorting the array will take O(N* logN). Overall searchTriplet() will take O(N * logN + N^2),
     * which is asymptotically equivalent to O(N^2).
     *
     * Space complexity
     * The space complexity of the above algorithm will be O(N) which is required for sorting.
     *
     * @param arr
     * @param targetSum
     * @return
     */
    public static int searchTriplet(int[] arr, int targetSum) {
        Arrays.sort(arr);
        int smallestDifference = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1, right = arr.length - 1;
            while (left < right) {
                // comparing the sum of three numbers to the 'targetSum' can cause overflow
                // so, we will try to find a target difference
                int targetDiff = targetSum - arr[i] - arr[left] - arr[right];
                if (targetDiff == 0) //  we've found a triplet with an exact sum
                    return targetSum - targetDiff; // return sum of all the numbers

                if (Math.abs(targetDiff) < Math.abs(smallestDifference))
                    smallestDifference = targetDiff; // save the closest difference

                if (targetDiff > 0)
                    left++; // we need a triplet with a bigger sum
                else
                    right--; // we need a triplet with a smaller sum
            }
        }
        return targetSum - smallestDifference;
    }

    public static void main(String[] args) {
        System.out.println(P02_05_TripletSumCloseToTarget.searchTriplet(new int[] { -2, 0, 1, 2 }, 2));
        System.out.println(P02_05_TripletSumCloseToTarget.searchTriplet(new int[] { -3, -1, 1, 2 }, 1));
        System.out.println(P02_05_TripletSumCloseToTarget.searchTriplet(new int[] { 1, 0, 1, 1 }, 100));
    }
}