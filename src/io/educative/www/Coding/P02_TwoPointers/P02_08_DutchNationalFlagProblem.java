package io.educative.www.Coding.P02_TwoPointers;

class P02_08_DutchNationalFlagProblem {
    /**
     * Time complexity
     * The time complexity of the above algorithm will be O(N) as we are iterating the input array only once.
     *
     * Space complexity
     * The algorithm runs in constant space O(1).
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        // all elements < low are 0 and all elements > high are 2
        // all elements from >= low < i are 1
        int low = 0, high = arr.length - 1;
        for (int i = 0; i <= high;) {
            if (arr[i] == 0) {
                swap(arr, i, low);
                // increment 'i' and 'low'
                i++;
                low++;
            } else if (arr[i] == 1) {
                i++;
            } else { // the case for arr[i] == 2
                swap(arr, i, high);
                // decrement 'high' only, after the swap the number at index 'i' could be 0, 1 or 2
                high--;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 0, 2, 1, 0 };
        P02_08_DutchNationalFlagProblem.sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();

        arr = new int[] { 2, 2, 0, 1, 2, 0 };
        P02_08_DutchNationalFlagProblem.sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
    }
}