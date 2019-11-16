package io.educative.www.Coding.P02_TwoPointers;

class P02_02_RemoveDuplicates {
    /**
     * Time Complexity
     * The time complexity of the above algorithm will be O(N), where ‘N’ is the total number of elements in the given array.
     *
     * Space Complexity
     * The algorithm runs in constant space O(1).
     *
     * @param arr
     * @return
     */
    public static int remove(int[] arr) {
        int nextNonDuplicate = 1; // index of the next non-duplicate element
        for (int i = 1; i < arr.length; i++) {
            if (arr[nextNonDuplicate - 1] != arr[i]) {
                arr[nextNonDuplicate] = arr[i];
                nextNonDuplicate++;
            }
        }

        return nextNonDuplicate;
    }

    /**
     * Problem 1: Given an unsorted array of numbers and a target ‘key’, remove all instances of ‘key’ in-place and
     * return the new length of the array.
     *
     * Time and Space Complexity: The time complexity of the above algorithm will be O(N), where ‘N’ is the total number
     * of elements in the given array.
     *
     * The algorithm runs in constant space O(1).
     *
     * @param arr
     * @param key
     * @return
     */
    public static int remove(int[] arr, int key) {
        int nextElement = 0; // index of the next element which is not 'key'
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != key) {
                arr[nextElement] = arr[i];
                nextElement++;
            }
        }

        return nextElement;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 2, 3, 3, 3, 6, 9, 9 };
        System.out.println(P02_02_RemoveDuplicates.remove(arr));

        arr = new int[] { 2, 2, 2, 11 };
        System.out.println(P02_02_RemoveDuplicates.remove(arr));
    }
}