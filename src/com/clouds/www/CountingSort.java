package com.clouds.www;

/**
 * time: O(n + r), space: O(n + r), stable: yes
 */
public class CountingSort {
    public int[] countingSort(int[] arr) {
        int max = arr[0];
        int min = arr[0];

        // find max and min
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        // init count[]
        int range = max - min + 1;
        int[] count = new int[range];

        for (int i = 0; i < range; i++) {
            count[arr[i] - min]++;
        }

        // modify count[] represents ending points
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        // scan from right for results
        int[] res = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            res[--count[arr[i] - min]] = arr[i];
        }

        return res;
    }
}
