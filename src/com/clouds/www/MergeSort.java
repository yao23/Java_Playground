package com.clouds.www;

public class MergeSort {
    public int[] mergeSort(int[] arr) { // time: O(nlogn), space: O(n)
        if (arr == null) {
            return arr;
        }
        int[] helper = new int[arr.length];
        doSort(arr, helper, 0, arr.length -1);
        return arr;
    }

    private void doSort(int[] arr, int[] helper, int start, int end) {
        if (start >= end) {
            return;
        }

        // binary deduction
        int mid = start + (end - start) / 2;
        doSort(arr, helper, start, mid);
        doSort(arr, helper, mid + 1, end);

        merge(arr, helper, start, mid, end);
    }

    private void merge(int[] arr, int[] helper, int aStart, int aEnd, int bEnd) {
        // copy arr from aStart to bEnd
        for (int i = aStart; i <= bEnd; i++) {
            helper[i] = arr[i];
        }

        int aCur = aStart;
        int bCur = aEnd + 1;

        for (int i = aStart; i <= bEnd; i++) {
            // one exausts
            if (aCur > aEnd) {
                arr[i] = helper[bCur];
                bCur++;
            } else if (bCur > bEnd) {
                arr[i] = helper[aCur];
                aCur++;
            } else if (helper[aCur] < helper[bCur]) {
                arr[i] = helper[aCur];
                aCur++;
            } else {
                arr[i] = helper[bCur];
                bCur++;
            }
        }
    }
}
