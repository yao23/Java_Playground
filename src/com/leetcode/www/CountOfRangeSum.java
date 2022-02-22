package com.leetcode.www;

public class CountOfRangeSum { // LC 327

    /**
      * Recall count smaller number after self where we encountered the problem

      * count[i] = count of nums[j] - nums[i] < 0 with j > i
      * Here, after we did the preprocess, we need to solve the problem

      * count[i] = count of a <= S[j] - S[i] <= b with j > i
      * ans = sum(count[:])
      * Therefore the two problems are almost the same. We can use the same technique used in that problem to solve this problem. One solution is merge sort based; another one is Balanced BST based. The time complexity are both O(n log n).

      * The merge sort based solution counts the answer while doing the merge. During the merge stage, we have already sorted the left half [start, mid) and right half [mid, end). We then iterate through the left half with index i. For each i, we need to find two indices k and j in the right half where

      * j is the first index satisfy sums[j] - sums[i] > upper and
     k is the first index satisfy sums[k] - sums[i] >= lower.
     Then the number of sums in [lower, upper] is j-k. We also use another index t to copy the elements satisfy sums[t] < sums[i] to a cache in order to complete the merge sort.

     Despite the nested loops, the time complexity of the "merge & count" stage is still linear. Because the indices k, j, t will only increase but not decrease, each of them will only traversal the right half once at most. The total time complexity of this divide and conquer solution is then O(n log n).

     One other concern is that the sums may overflow integer. So we use long instead.
      * // beats 66.49%
     */
    public static int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1]; // pre-processed sums (hidden condition: sum element has index range info, don't worry about index messed after sorted
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        return countWhileMergeSort(sums, 0, n + 1, lower, upper);
    }

    private static int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
        if (end - start <= 1) {
            return 0;
        }
        int mid = start + (end - start) / 2;
        // find matched range sum in left and right halves
        int count = countWhileMergeSort(sums, start, mid, lower, upper)
                + countWhileMergeSort(sums, mid, end, lower, upper); // [mid, end] for right half
        int j = mid, k = mid, t = mid;
        long[] cache = new long[end - start];
        // find matched range sum cross left and right halves
        for (int i = start, r = 0; i < mid; i++, r++) {
            while (k < end && sums[k] - sums[i] < lower) { // 1st index for lower in right
                k++;
            }
            while (j < end && sums[j] - sums[i] <= upper) { // 1st index for upper in right
                j++;
            }
            while (t < end && sums[t] < sums[i]) { // find all smaller sum in right half for merge later
                cache[r++] = sums[t++];
            }
            cache[r] = sums[i];
            count += j - k;
        }
        System.arraycopy(cache, 0, sums, start, t - start); // merge
        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 5, -1};
        int lower = -2;
        int upper = 2;

        System.out.println("num of range sum: " + countRangeSum(nums, lower, upper));
    }
}
