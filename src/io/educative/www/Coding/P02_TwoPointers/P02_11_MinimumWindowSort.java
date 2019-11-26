package io.educative.www.Coding.P02_TwoPointers;

class P02_11_MinimumWindowSort {
    /**
     * Time complexity
     * The time complexity of the above algorithm will be O(N).
     *
     * Space complexity
     * The algorithm runs in constant space O(1).
     *
     * @param arr
     * @return
     */
    public static int sort(int[] arr) {
        int low = 0, high = arr.length - 1;
        // find the first number out of sorting order from the beginning
        while (low < arr.length - 1 && arr[low] <= arr[low + 1])
            low++;

        if (low == arr.length - 1) // if the array is sorted
            return 0;

        // find the first number out of sorting order from the end
        while (high > 0 && arr[high] >= arr[high - 1])
            high--;

        // find the maximum and minimum of the subarray
        int subarrayMax = Integer.MIN_VALUE, subarrayMin = Integer.MAX_VALUE;
        for (int k = low; k <= high; k++) {
            subarrayMax = Math.max(subarrayMax, arr[k]);
            subarrayMin = Math.min(subarrayMin, arr[k]);
        }

        // extend the subarray to include any number which is bigger than the minimum of the subarray 
        while (low > 0 && arr[low - 1] > subarrayMin)
            low--;
        // extend the subarray to include any number which is smaller than the maximum of the subarray
        while (high < arr.length - 1 && arr[high + 1] < subarrayMax)
            high++;

        return high - low + 1;
    }

    public static void main(String[] args) {
        System.out.println(P02_11_MinimumWindowSort.sort(new int[] { 1, 2, 5, 3, 7, 10, 9, 12 }));
        System.out.println(P02_11_MinimumWindowSort.sort(new int[] { 1, 3, 2, 0, -1, 7, 10 }));
        System.out.println(P02_11_MinimumWindowSort.sort(new int[] { 1, 2, 3 }));
        System.out.println(P02_11_MinimumWindowSort.sort(new int[] { 3, 2, 1 }));
    }
}