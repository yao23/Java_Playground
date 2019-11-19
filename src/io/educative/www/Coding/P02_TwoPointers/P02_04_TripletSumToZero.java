package io.educative.www.Coding.P02_TwoPointers;

import java.util.*;

class P02_04_TripletSumToZero {
    /**
     * Time complexity
     * Sorting the array will take O(N * logN). The searchPair() function will take O(N). As we are calling searchPair()
     * for every number in the input array, this means that overall searchTriplets() will take O(N * logN + N^2),
     * which is asymptotically equivalent to O(N^2).
     *
     * Space complexity
     * Ignoring the space required for the output array, the space complexity of the above algorithm will be O(N)
     * which is required for sorting.
     *
     * @param arr
     * @return
     */
    public static List<List<Integer>> searchTriplets(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> triplets = new ArrayList<>();
        for (int i = 0; i < arr.length - 2; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) // skip same element to avoid duplicate triplets
                continue;
            searchPair(arr, -arr[i], i + 1, triplets);
        }

        return triplets;
    }

    private static void searchPair(int[] arr, int targetSum, int left, List<List<Integer>> triplets) {
        int right = arr.length - 1;
        while (left < right) {
            // comparing the sum of two numbers to the 'targetSum' can cause integer overflow
            // so, we will try to find a target difference instead
            int targetDiff = targetSum - arr[left];
            if (targetDiff == arr[right]) { // found the triplet
                triplets.add(Arrays.asList(-targetSum, arr[left], arr[right]));
                left++;
                right--;
                while (left < right && arr[left] == arr[left - 1])
                    left++; // skip same element to avoid duplicate triplets
                while (left < right && arr[right] == arr[right + 1])
                    right--; // skip same element to avoid duplicate triplets
            } else if (targetDiff > arr[right])
                left++; // we need a pair with a bigger sum
            else
                right--; // we need a pair with a smaller sum
        }
    }

    public static void main(String[] args) {
        System.out.println(P02_04_TripletSumToZero.searchTriplets(new int[] { -3, 0, 1, 2, -1, 1, -2 }));
        System.out.println(P02_04_TripletSumToZero.searchTriplets(new int[] { -5, 2, -1, -2, 3 }));
    }
}