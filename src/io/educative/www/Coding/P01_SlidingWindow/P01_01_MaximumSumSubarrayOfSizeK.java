package io.educative.www.Coding.P01_SlidingWindow;

class P01_01_MaximumSumSubarrayOfSizeK {
    /**
     * The time complexity of the above algorithm will be O(N*K), where ‘N’ is the total number of elements in the given array.
     *
     * @param k
     * @param arr
     * @return
     */
    public static int findMaxSumSubArray(int k, int[] arr) {
        int maxSum = 0, windowSum;
        for (int i = 0; i <= arr.length - k; i++) {
            windowSum = 0;
            for (int j = i; j < i + k; j++) {
                windowSum += arr[j];
            }
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    /**
     * Time Complexity
     * The time complexity of the above algorithm will be O(N).
     *
     * Space Complexity
     * The algorithm runs in constant space O(1).
     *
     * @param k
     * @param arr
     * @return
     */
    public static int findMaxSumSubArrayV1(int k, int[] arr) {
        int windowSum = 0, maxSum = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd]; // add the next element
            // slide the window, we don't need to slide if we've not hit the required window size of 'k'
            if (windowEnd >= k - 1) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= arr[windowStart]; // subtract the element going out
                windowStart++; // slide the window ahead
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println("Maximum sum of a subarray of size K: "
                + P01_01_MaximumSumSubarrayOfSizeK.findMaxSumSubArray(3, new int[] { 2, 1, 5, 1, 3, 2 }));
        System.out.println("Maximum sum of a subarray of size K: "
                + P01_01_MaximumSumSubarrayOfSizeK.findMaxSumSubArray(2, new int[] { 2, 3, 4, 1, 5 }));
    }
}