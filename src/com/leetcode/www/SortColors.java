package com.leetcode.www;

/**
 * Created by liyao on 6/3/17.
 */

public class SortColors { // LC 75
    private static void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    private static int sortHelper(int[] nums, int i, int j, boolean shiftLeft) {
        while (i < j) {
            while (nums[i] == 0 && i < j) { // find white or blue in left side
                i++;
            }

            while (nums[j] == 2 && i < j) { // find white or red in right side
                j--;
            }

            if (i < j) {
                if (nums[i] == 1 && nums[j] == 1) { // left and right are white, but some colors are in middle
                    if (shiftLeft) {
                        j--;
                    } else {
                        i++;
                    }
                } else {
                    swap(nums, i, j);
                }
            }
        }

        return j;
    }

    public static void sortColors(int[] nums) { // beats 5.63%, two pointers
        if (nums.length <= 1) { // array length is 0 or 1
            return;
        }

        int i = 0, j = nums.length - 1;

        j = sortHelper(nums, i, j, true);

        boolean isInOrder = true;

        for (int m = 1; m < nums.length; m++) {
            if (nums[m - 1] > nums[m]) {
                isInOrder = false;
                break;
            } else {
                continue;
            }
        }

        if (!isInOrder) {
            i = j;
            j = nums.length - 1;

            sortHelper(nums, i, j, false);
        }
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Sort Colors.
     * Memory Usage: 34.5 MB, less than 100.00% of Java online submissions for Sort Colors.
     *
     * @param nums
     */
    public static void sortColors1(int[] nums) { // beats 5.63%, three pointers
        int numLength = nums.length;
        if (numLength <= 1) {
            return;
        } else {
            int curIdx = 0, redIdx = 0, blueIdx = numLength - 1;

            while (curIdx <= blueIdx) {

                int curElement = nums[curIdx];
                if (curElement == 0) { // red
                    if (curIdx != redIdx) {
                        swap(nums, redIdx, curIdx);
                    }

                    redIdx++;
                    curIdx++;
                } else if (curElement == 2) { // blue
                    if (curIdx != blueIdx) {
                        swap(nums, curIdx, blueIdx);
                    }

                    blueIdx--;
                } else { // green
                    curIdx++;
                }
            }
        }
    }

    private static void testResults(int[] arr, int testCaseIdx, int solutionOption) {
        System.out.println("test case " + testCaseIdx);

        for (int inputElement : arr) {
            System.out.print(inputElement + " ");
        }

        long tStart = System.nanoTime();

        if (solutionOption == 0) {
            sortColors(arr);
        } else {
            sortColors1(arr);
        }

        long tEnd = System.nanoTime();
        long tRes = tEnd - tStart; // time in nanoseconds
        double elapsedSeconds = tRes / 1000000000.0;

        System.out.println();
        System.out.println("time: " + elapsedSeconds);

        for (int outputElement : arr) {
            System.out.print(outputElement + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {

        int[] arr0 = new int[] {2, 1, 2, 0, 0};
        int[] arr1 = new int[] {2, 2, 1, 0, 0};
        int[] arr2 = new int[] {2, 2, 1, 1, 0, 0, 0, 0};
        int[] arr3 = new int[] {2, 2, 1, 0, 0, 1, 1, 0, 0, 0, 0};
        int[] arr4 = new int[] {2, 2, 1, 0, 2, 1, 2, 0, 1, 1, 0, 0, 0, 0};

        // case 0
        testResults(arr0, 0, 0); // time: 4.0E-6
//        testResults(arr0, 0, 1); // time: 3.0E-6

        // case 1
        testResults(arr1, 1, 0); // time: 1.0E-6
//        testResults(arr1, 1, 1); // time: 1.0E-6

        // case 2
        testResults(arr2, 2, 0); // time: 2.0E-6
//        testResults(arr2, 2, 1); // time: 2.0E-6

        // case 3
        testResults(arr3, 3, 0); // time: 2.0E-6
//        testResults(arr3, 3, 1); // time: 2.0E-6

        // case 4
        testResults(arr4, 4, 0); // time: 3.0E-6
//        testResults(arr4, 4, 1); // time: 2.0E-6
    }
}
