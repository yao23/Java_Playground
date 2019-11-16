package io.educative.www.Coding.P02_TwoPointers;

import java.util.HashMap;

class P02_01_PairWithTargetSum {
    /**
     * Time Complexity
     * The time complexity of the above algorithm will be O(N), where ‘N’ is the total number of elements in the given array.
     *
     * Space Complexity
     * The algorithm runs in constant space O(1).
     *
     * @param arr
     * @param targetSum
     * @return
     */
    public static int[] search(int[] arr, int targetSum) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            // comparing the sum of two numbers to the 'targetSum' can cause integer overflow
            // so, we will try to find a target difference instead
            int targetDiff = targetSum - arr[left];
            if (targetDiff == arr[right])
                return new int[] { left, right }; // found the pair

            if (targetDiff > arr[right])
                left++; // we need a pair with a bigger sum
            else
                right--; // we need a pair with a smaller sum
        }
        return new int[] { -1, -1 };
    }

    /**
     * Time Complexity
     * The time complexity of the above algorithm will be O(N), where ‘N’ is the total number of elements in the given array.
     *
     * Space Complexity
     * The space complexity will also be O(N), as, in the worst case, we will be pushing ‘N’ numbers in the HashTable.
     *
     * @param arr
     * @param targetSum
     * @return
     */
    public static int[] searchV1(int[] arr, int targetSum) {
        HashMap<Integer, Integer> nums = new HashMap<>(); // to store numbers and their indices
        for (int i = 0; i < arr.length; i++) {
            if (nums.containsKey(targetSum - arr[i]))
                return new int[] { nums.get(targetSum - arr[i]), i };
            else
                nums.put(arr[i], i); // put the number and its index in the map
        }
        return new int[] { -1, -1 }; // pair not found
    }

    public static void main(String[] args) {
        int[] result = P02_01_PairWithTargetSum.search(new int[] { 1, 2, 3, 4, 6 }, 6);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
        result = P02_01_PairWithTargetSum.search(new int[] { 2, 5, 9, 11 }, 11);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
    }
}