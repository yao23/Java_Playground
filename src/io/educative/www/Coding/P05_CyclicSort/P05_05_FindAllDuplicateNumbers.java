package io.educative.www.Coding.P05_CyclicSort;

import java.util.*;

class P05_05_FindAllDuplicateNumbers {
    /**
     * Time complexity
     * The time complexity of the above algorithm is O(n).
     *
     * Space complexity
     * Ignoring the space required for storing the duplicates, the algorithm runs in constant space O(1).
     *
     * @param nums
     * @return
     */
    public static List<Integer> findNumbers(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != nums[nums[i] - 1])
                swap(nums, i, nums[i] - 1);
            else
                i++;
        }

        List<Integer> duplicateNumbers = new ArrayList<>();
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1)
                duplicateNumbers.add(nums[i]);
        }

        return duplicateNumbers;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        List<Integer> duplicates = P05_05_FindAllDuplicateNumbers.findNumbers(new int[] { 3, 4, 4, 5, 5 });
        System.out.println("Duplicates are: " + duplicates);

        duplicates = P05_05_FindAllDuplicateNumbers.findNumbers(new int[] { 5, 4, 7, 2, 3, 5, 3 });
        System.out.println("Duplicates are: " + duplicates);
    }
}
