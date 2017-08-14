package com.clouds.www;

/**
 * Stability: No relative partition position guaranteed
 * stability: No
 */
public class QuickSort { // time: average O(nlogn), worst O(n^2), space: O(1)
    private void doQuickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int partitionPos = partition(arr, start, end);

        doQuickSort(arr, start, partitionPos);
        doQuickSort(arr, partitionPos + 1, end);
    }

    private int partition(int[] arr, int left, int right) {
        int ran = left + (int)(Math.random() * (right - left + 1));
        int pivot = arr[ran];
        // switch pivot to the leftmost
        arr[ran] = arr[left];
        arr[left] = pivot;

        while (left < right) {
            // find a smaller element from right
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            // put to the left
            arr[left] = arr[right];
            // find a larger element from left
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            // put to the right
            arr[right] = arr[left];
        }

        // put the pivot back
        arr[left] = pivot;
        return left;
    }
}
