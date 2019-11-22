package io.educative.www.Coding.P02_TwoPointers;

import java.util.*;

class P02_07_SubarraysWithProductLessThanATarget {
    /**
     * Time complexity
     * The main for-loop managing the sliding window takes O(N) but creating subarrays can take up to O(N^2) in the
     * worst case. Therefore overall, our algorithm will take O(N^3).
     *
     * Space complexity
     * Ignoring the space required for the output list, the algorithm runs in O(N) space which is used for the temp list.
     *
     * Can you try estimating how much space will be required for the output list?
     *
     * @param arr
     * @param target
     * @return
     */
    public static List<List<Integer>> findSubarrays(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int product = 1, left = 0;
        for (int right = 0; right < arr.length; right++) {
            product *= arr[right];
            while (product >= target && left < arr.length)
                product /= arr[left++];
            // since the product of all numbers from left to right is less than the target therefore,
            // all subarrays from lef to right will have a product less than the target too; to avoid
            // duplicates, we will start with a subarray containing only arr[right] and then extend it
            List<Integer> tempList = new LinkedList<>();
            for (int i = right; i >= left; i--) {
                tempList.add(0, arr[i]);
                result.add(new ArrayList<>(tempList));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(P02_07_SubarraysWithProductLessThanATarget.findSubarrays(new int[] { 2, 5, 3, 10 }, 30));
        System.out.println(P02_07_SubarraysWithProductLessThanATarget.findSubarrays(new int[] { 8, 2, 6, 5 }, 50));
    }
}